package org.or5e.core.plugin.pes;

import org.or5e.core.plugin.event.io.EventConsumer;

public interface EventRegistration {
	public void registerForEventFromQueue(EventConsumer _requestHandler, String... events);
	public void raiseEventToQueue(String source, String eventName, Object eventMessage);
}
