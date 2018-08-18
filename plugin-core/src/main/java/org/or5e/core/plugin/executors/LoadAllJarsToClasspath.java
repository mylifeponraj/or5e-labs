package org.or5e.core.plugin.executors;

import java.io.File;

import org.or5e.core.BaseObject;
import org.or5e.core.filefilter.JarFileFilter;

public class LoadAllJarsToClasspath extends BaseObject implements TaskExecute<File[], Void> {

	@Override public String getName() {
		return "org.or5e.core.plugin.executors.LoadAllJarsToClasspath";
	}

	@Override public File[] executeTask(Void noParam) {
		File pluginFolder = new File(getProperties("pluginFolder"));
		debug("Searching plugins in: "+pluginFolder.getAbsolutePath());
		File[] listOfPluginJars = pluginFolder.listFiles(new JarFileFilter());
		return listOfPluginJars;
	}
}
