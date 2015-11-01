package org.or5e.core.plugin.event.io;

public interface PluginEventOutputHandler {
	public void eventRaised(String pluginID, String message, Object data);
}
