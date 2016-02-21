package org.or5e.core.plugin.event.io;

import org.or5e.core.plugin.event.EventMessage;

public interface EventConsumer {
	public void listenToEvent(EventMessage message);
}