package org.or5e.plugin.manage;

import java.util.Map;

import org.or5e.core.BaseObject;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginEvent;
import org.or5e.core.plugin.PluginLoaderSPI;

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
	public Map<String, Plugin> getPluginTableData() {
		Map<String, Plugin> listAllPlugin = pluginSPI.listAllPlugin();
		return listAllPlugin;
	}
	
	public void loadAllPluginDetails() {
		
	}
	@Override public String getName() {
		return "org.or5e.plugin.manage.PluginUIManager";
	}
	public void destroy() {
		this.pluginSPI.destroy();
	}
}
