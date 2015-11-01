package org.or5e.core.plugin.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.or5e.core.BaseObject;
import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.event.io.PluginEventInputHandler;

public class PluginEventQueue extends BaseObject implements EventQueue{
	private Map<String, List<PluginEventInputHandler>> _listeners = null;
	private Map<String, String> _registeredPlugin = null;
	private String _queueName;
	public static final PluginEventQueue _queue;
	private static Map<String, PluginEventQueue> _customQueue = new HashMap<>();
	static {
		_queue = new PluginEventQueue("Default");
	}
	private PluginEventQueue(String queueName) {
		_listeners = new HashMap<String, List<PluginEventInputHandler>>();
		_registeredPlugin = new HashMap<String, String>();
		this._queueName = queueName;
	}
	public static EventQueue getDefaultQueue() {
		return _queue;
	}
	public static EventQueue getQueue(String queueName) {
		if(_customQueue.get(queueName) != null) {
			return _customQueue.get(queueName);
		}
		else {
			PluginEventQueue newQueue = new PluginEventQueue(queueName);
			_customQueue.put(queueName, newQueue);
			return newQueue;
		}
	}
	@Override public void registerEventToQueue(Plugin plugin, String... evtRaise) throws PluginException {
		for (String event : evtRaise) {
			if(_registeredPlugin.get(event) != null) {
				throw new PluginException("Event "+event+" is already Registered to Plugin["+plugin.getPluginID()+"]");
			}
			List<PluginEventInputHandler> listenerQueue = new ArrayList<PluginEventInputHandler>();
			_listeners.put(event, listenerQueue);
			_registeredPlugin.put(event, plugin.getPluginID());
		}
	}
	@Override public void listenToQueue(PluginEventInputHandler _handler, String... listenToCommands) {
		for (String event : listenToCommands) {
			List<PluginEventInputHandler> handlerList = _listeners.get(event);
			if(handlerList == null) {
				handlerList = new ArrayList<PluginEventInputHandler>();
				_listeners.put(event, handlerList);
			}
			handlerList.add(_handler);
		}
	}
	@Override public void raiseEventToQueue(Plugin plugin, String eventName, Object eventData) {
		List<PluginEventInputHandler> eventListeners = _listeners.get(eventName);
		if(eventListeners != null) {
			EventMessage message = new EventMessage();
			message.setEventName(eventName);
			message.setEventSource(plugin.getPluginID());
			message.setEventData(eventData);
			for (PluginEventInputHandler pluginEventHandler : eventListeners) {
				pluginEventHandler.eventRecived(message);
			}
		}
	}
	@Override public Set<String> getAllCommandsListened() {
		return _listeners.keySet();
	}

	@Override public String getName() {
		return "PluginEventQueue";
	}
	@Override public String getEventQueueName() {
		return this._queueName;
	}
}