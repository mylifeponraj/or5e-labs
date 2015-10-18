package org.or5e.core.plugin;

import java.util.Map;

public interface PluginManager extends Lifecycle{
	public void disablePlugin(String pluginID);
	public void enablePlugin(String pluginID);
	public Map<String, Plugin> listAllPlugin();
}
