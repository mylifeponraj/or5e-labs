package org.or5e.core.plugin;

import java.io.File;

import org.or5e.core.BaseObject;

public class MyPlugin extends PluginLifecycleAdaptor implements Plugin {

	@Override
	public void initilize() {
		info("Initilize...");
		System.out.println("File Object: "+ new File(BaseObject.getProperties("pluginFolder")).isDirectory());
	}

	@Override
	public void destroy() {
		info("Destroy...");

	}

	@Override
	public void doProcess() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "MyPlugin";
	}
	
	public static void main(String[] args) {
		new MyPlugin();
		System.out.println("Done");
	}

	@Override public String getPluginID() {
		return "MyPlugin";
	}
}
