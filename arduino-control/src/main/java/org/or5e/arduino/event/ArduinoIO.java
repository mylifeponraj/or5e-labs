package org.or5e.arduino.event;

import java.util.List;

import org.or5e.core.BaseObject;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.event.EventQueue;
import org.or5e.core.plugin.event.PluginEventQueue;

public class ArduinoIO extends BaseObject{
	public static ArduinoIO _manager;
	protected List<String> eventToBeRaised = null;
	protected List<String> eventToBeListened = null;
	private InputEventProcessor _inputProcessor;
	private OutputEventProcessor _outputProcessor;
	private EventQueue _queue;

	public static ArduinoIO initilizeArduinoIO(Plugin _plugin) {
		if(_manager == null) {
			_manager = new ArduinoIO();
			_manager._queue = PluginEventQueue.getDefaultQueue();

			_manager._inputProcessor = new InputEventProcessor(_manager._queue);
			_manager._inputProcessor.initilize();

			_manager._outputProcessor = new OutputEventProcessor(_plugin, _manager._queue);
			_manager._outputProcessor.initilize();
		}
		
		return _manager;
	}
	public void raiseEvent(String eventMessage, Object eventData) {
		this._outputProcessor.raiseEvent(eventMessage, eventData);
	}
	public void initilize(){}

	@Override public String getName() {
		return "ArduinoIO";
	}
}