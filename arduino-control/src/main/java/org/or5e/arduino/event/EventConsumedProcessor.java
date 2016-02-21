package org.or5e.arduino.event;

import java.util.ArrayList;

import org.or5e.core.plugin.event.EventMessage;
import org.or5e.core.plugin.event.EventQueue;
import org.or5e.core.plugin.event.io.EventConsumer;

public class EventConsumedProcessor extends ArduinoIO implements EventConsumer {
	public EventQueue _queue = null;
	public EventConsumedProcessor(EventQueue queue) {
		this._queue = queue;
	}
	@Override public void initilize() {
		eventToBeListened = new ArrayList<String>();
		eventToBeListened.add("BeepSound");
		eventToBeListened.add("RGBValue");
		
		_queue.listenForEventInQueue(this, eventToBeListened.toArray(new String[this.eventToBeListened.size()]));
	}
	@Override public void listenToEvent(EventMessage message) {
		info("Event Received for: "+message.getEventName());
	}
}
