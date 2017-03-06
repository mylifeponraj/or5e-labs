package org.or5e.core.plugin;

import org.or5e.core.PropertiesProcessor;
import org.or5e.core.plugin.event.EventMessage;
import org.or5e.core.plugin.event.io.EventConsumer;

public class PropertiesEventProcessor extends PropertiesProcessor implements EventConsumer{
	
	@Override public String getName() {
		return "PropertiesEventProcessor";
	}

	@Override public void listenToEvent(EventMessage message) {
		System.out.println(message.getEventName()+":"+message.getEventData());
	}

}
