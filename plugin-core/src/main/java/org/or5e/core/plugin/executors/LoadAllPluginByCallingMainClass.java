package org.or5e.core.plugin.executors;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.Set;

import org.or5e.core.BaseObject;
import org.or5e.core.PluginException;

public class LoadAllPluginByCallingMainClass extends BaseObject implements TaskExecute<Void, Map<URL, String>> {
	private URLClassLoader urlClassLoader = null;
	@Override public String getName() {
		return "org.or5e.core.plugin.executors.LoadAllPluginByCallingMainClass";
	}

	@Override public Void executeTask(Map<URL, String> pluginMainclassMap) {
		Set<URL> urlKeySet = pluginMainclassMap.keySet();
		URL[] urls = new URL[urlKeySet.size()];
		int index = 0;
		for (URL url : urlKeySet) {
			urls[index++] = url;
		}
		if(urlClassLoader == null) {
			urlClassLoader = new URLClassLoader(urls, this.getClass().getClassLoader());
		}
		else {
			throw new PluginException("Plugin Manager Already Loaded... You cannot Load Two times...");
		}

		return null;
	}

}
