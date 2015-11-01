package org.or5e.core.plugin.pes;

import org.or5e.core.plugin.event.io.PluginEventInputHandler;

public interface EventRegistration {
	public void registerForEventFromQueue(PluginEventInputHandler _requestHandler, String... events);
	public void raiseEventToQueue(String source, String eventName, Object eventMessage);
}
