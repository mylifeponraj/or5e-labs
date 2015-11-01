package org.or5e.arduino.event;

import java.util.ArrayList;

import org.or5e.core.plugin.event.EventMessage;
import org.or5e.core.plugin.event.EventQueue;
import org.or5e.core.plugin.event.io.PluginEventInputHandler;

public class InputEventProcessor extends ArduinoIO implements PluginEventInputHandler {
	public EventQueue _queue = null;
	public InputEventProcessor(EventQueue queue) {
		this._queue = queue;
	}
	@Override public void initilize() {
		eventToBeListened = new ArrayList<String>();
		eventToBeListened.add("BeepSound");
		eventToBeListened.add("RGBValue");
		eventToBeListened.add("TempValue");
		eventToBeListened.add("LightValue");
		eventToBeListened.add("3AxisValue");
		
		_queue.listenToQueue(this, eventToBeListened.toArray(new String[this.eventToBeListened.size()]));
	}
	@Override public void eventRecived(EventMessage message) {
		info("Event Received for: "+message.getEventName());
	}
}
