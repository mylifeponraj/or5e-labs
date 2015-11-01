package org.or5e.core.plugin.event.io;

import org.or5e.core.plugin.event.EventMessage;

public interface PluginEventInputHandler {
	public void eventRecived(EventMessage message);
}