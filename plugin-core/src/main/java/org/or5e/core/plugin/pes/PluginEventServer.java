package org.or5e.core.plugin.pes;

import org.or5e.core.plugin.Lifecycle;

public interface PluginEventServer extends Lifecycle, EventRegistration {
	public void startEventServer();
	public void stopEventServer();
}
