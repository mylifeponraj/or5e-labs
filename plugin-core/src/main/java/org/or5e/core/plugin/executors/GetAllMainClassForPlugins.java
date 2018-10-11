package org.or5e.core.plugin.executors;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import org.or5e.core.BaseObject;

public class GetAllMainClassForPlugins extends BaseObject implements TaskExecute<Map<URL, String>, File[]> {

	@Override public String getName() {
		return "org.or5e.core.plugin.executors.GetAllMainClassForPlugins";
	}

	@Override public Map<URL, String> executeTask(File[] listOfPluginJars) {
		Map<URL, String> pluginMainclassMap = new HashMap<URL, String>(listOfPluginJars.length);
		for (File pluginJar : listOfPluginJars) {
			try {
				JarInputStream jarStream = new JarInputStream(new FileInputStream(pluginJar));
				Manifest maniFest = jarStream.getManifest();
				Attributes attributes = maniFest.getMainAttributes();
				String mainClass = attributes.getValue("main-class");
				debug("Main Class["+pluginJar.toURL()+"]: "+mainClass);
				if(mainClass != null) {
					pluginMainclassMap.put(pluginJar.toURL(), mainClass);
				}
				jarStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pluginMainclassMap;
	}

}
