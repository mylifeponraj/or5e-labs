package org.or5e.core.plugin;

import java.util.Map;

import org.or5e.core.plugin.event.PluginEvent;

public interface PluginLoader extends Lifecycle{
	public Map<String, Plugin> listAllPlugin();
	public void initilize(PluginEvent event);
}
