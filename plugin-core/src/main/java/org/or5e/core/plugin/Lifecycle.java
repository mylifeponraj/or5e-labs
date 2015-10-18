package org.or5e.core.plugin;

public interface Lifecycle {
	public void initilize() throws PluginException;
	public void destroy();
}