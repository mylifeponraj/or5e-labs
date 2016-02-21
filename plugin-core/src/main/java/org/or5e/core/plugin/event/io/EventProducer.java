package org.or5e.core.plugin.event.io;

public interface EventProducer {
	public void raiseEventMessage(String pluginID, String message, Object data);
}
