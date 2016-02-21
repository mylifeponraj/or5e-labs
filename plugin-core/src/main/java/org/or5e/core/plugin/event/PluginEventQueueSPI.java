package org.or5e.core.plugin.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.or5e.core.BaseObject;
import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.event.io.EventConsumer;

public class PluginEventQueueSPI extends BaseObject implements EventQueue{
	private Map<String, List<EventConsumer>> _listeners = null;
	private Map<String, String> _registeredPlugin = null;
	private String _queueName;
	public static final PluginEventQueueSPI _defaultQueue;
	private static Map<String, PluginEventQueueSPI> _customQueue = new HashMap<>();
	static {
		_defaultQueue = new PluginEventQueueSPI("Default");
	}
	private PluginEventQueueSPI(String queueName) {
		_listeners = new HashMap<String, List<EventConsumer>>();
		_registeredPlugin = new HashMap<String, String>();
		this._queueName = queueName;
	}
	public static EventQueue getDefaultQueue() {
		return _defaultQueue;
	}
	public static EventQueue getQueue(String queueName) {
		if(_customQueue.get(queueName) != null) {
			return _customQueue.get(queueName);
		}
		else {
			PluginEventQueueSPI newQueue = new PluginEventQueueSPI(queueName);
			_customQueue.put(queueName, newQueue);
			return newQueue;
		}
	}
	@Override public Map<String, List<String>> getAllListener() {
		Set<String> commandsListening = _listeners.keySet();
		for (String commands : commandsListening) {
			List<EventConsumer> listener = _listeners.get(commands);
		}
		return null;
	}
	@Override public synchronized void publishEventToQueue(Plugin plugin, String... evtRaise) throws PluginException {
		for (String event : evtRaise) {
			if(_registeredPlugin.get(event) != null) {
				throw new PluginException("Event "+event+" is already Registered to Plugin["+plugin.getPluginID()+"]");
			}
			List<EventConsumer> listenerQueue = new ArrayList<EventConsumer>();
			_listeners.put(event, listenerQueue);
			_registeredPlugin.put(event, plugin.getPluginID());
		}
	}
	@Override public synchronized void listenForEventInQueue(EventConsumer _handler, String... listenToCommands) {
		for (String event : listenToCommands) {
			List<EventConsumer> handlerList = _listeners.get(event);
			if(handlerList == null) {
				handlerList = new ArrayList<EventConsumer>();
				_listeners.put(event, handlerList);
			}
			handlerList.add(_handler);
		}
	}

	@Override public void injectEventIntoQueue(Plugin plugin, String eventName, Object eventData) {
		List<EventConsumer> eventListeners = _listeners.get(eventName);
		if(eventListeners != null) {
			EventMessage message = new EventMessage();
			message.setEventName(eventName);
			message.setEventSource(plugin.getPluginID());
			message.setEventData(eventData);
			for (EventConsumer pluginEventHandler : eventListeners) {
				pluginEventHandler.listenToEvent(message);
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