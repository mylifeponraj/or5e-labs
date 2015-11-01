package org.or5e.core.plugin.event;

import java.util.Set;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.event.io.PluginEventInputHandler;

public interface EventQueue {
	public void registerEventToQueue(Plugin plugin, String... evtRaise) throws PluginException;
	public void listenToQueue(PluginEventInputHandler _handler, String... listenToCommands);
	public void raiseEventToQueue(Plugin plugin, String eventName, Object eventData);
	public Set<String> getAllCommandsListened();
	public String getEventQueueName();
}
