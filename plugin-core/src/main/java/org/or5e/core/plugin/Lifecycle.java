package org.or5e.core.plugin;

import org.or5e.core.PluginException;

public interface Lifecycle {
	public void initilize() throws PluginException;
	public void destroy();
}