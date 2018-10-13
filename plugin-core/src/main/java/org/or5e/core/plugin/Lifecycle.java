package org.or5e.core.plugin;

import org.or5e.core.PluginException;

public interface Lifecycle {
	public void initilizeService() throws PluginException;
	public void destroyService();
}