package org.or5e.core.plugin.pes;

import org.or5e.core.plugin.PluginEventHandler;

public interface EventRegistration {
	public void registerForEventFromQueue(PluginEventHandler _requestHandler, String... events);
	public void raiseEventToQueue(String source, String eventName, Object eventMessage);
}
