package org.or5e.core.plugin;

import java.util.Map;

public interface PluginLoader extends Lifecycle{
	public Map<String, Plugin> listAllPlugin();
}
