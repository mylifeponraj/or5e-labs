package org.or5e.core.plugin.event;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.event.io.EventConsumer;

public interface EventQueue {
	public void publishEventToQueue(Plugin plugin, String... evtRaise) throws PluginException;
	public void listenForEventInQueue(EventConsumer _handler, String... listenToCommands);
	public void injectEventIntoQueue(Plugin plugin, String eventName, Object eventData);
	public Set<String> getAllCommandsListened();
	public Map<String, List<String>> getAllListener();
	public String getEventQueueName();
}
