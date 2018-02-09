package org.or5e.plugin.manage;

import org.or5e.core.BaseObject;
import org.or5e.core.plugin.PluginLoaderSPI;
import org.or5e.core.plugin.event.PluginEvent;

public class PluginUIManager extends BaseObject {
	private PluginLoaderSPI pluginSPI = null;
	
	private static PluginUIManager _manager;
	public static PluginUIManager getManager () {
		if(_manager == null) _manager = new PluginUIManager();
		return _manager;
	}
	private PluginUIManager() { }

	public void initilizePlugin(PluginEvent event) {
		this.pluginSPI = PluginLoaderSPI.getPluginManager();
		this.pluginSPI.initilize(event);
	}
	@Override public String getName() {
		return "org.or5e.plugin.manage.PluginUIManager";
	}
	public void destroy() {
		this.pluginSPI.destroy();
	}
}
