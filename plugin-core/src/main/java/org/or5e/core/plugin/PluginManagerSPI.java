package org.or5e.core.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import org.or5e.core.BaseObject;
import org.or5e.core.PluginException;
import org.or5e.core.plugin.pes.PluginEventServer;
import org.or5e.core.plugin.pes.PluginEventServerSPI;

public class PluginManagerSPI extends BaseObject implements PluginManager {

	private URLClassLoader urlClassLoader = null;
	private Map<String, Plugin> pluginList = null;
	private PluginEventServer _server = null;

	@Override public void initilize()  throws PluginException{
		info("Initilizing Plugin Manager....");
		pluginList = new HashMap<>();
		_server = new PluginEventServerSPI();
		_server.initilize();
		_server.startEventServer();

		File[] listOfPluginJars = getAllJarFilesInPluginFolder();
		
		if(listOfPluginJars != null && listOfPluginJars.length > 0) {
			Map<URL, String> pluginMainclassMap = getPluginMainClassDetails(listOfPluginJars);
			loadAndRunMainClassInAllPlugins(pluginMainclassMap);
		}
	}

	@Override public void destroy() {
		_server.stopEventServer();
		_server.destroy();
		info("Destroy Plugin Manager....");
	}

	@Override  public void disablePlugin(String pluginID) {
	}

	@Override public void enablePlugin(String pluginID) {

	}

	@Override public Map<String, Plugin> listAllPlugin() {
		return this.pluginList;
	}

	private File[] getAllJarFilesInPluginFolder() {
		File pluginFolder = new File(getProperties("pluginFolder"));
		File[] listOfPluginJars = pluginFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File folder, String fileName) {
				return fileName.endsWith(".jar");
			}
		});
		return listOfPluginJars;
	}

	@SuppressWarnings("deprecation")
	private Map<URL, String> getPluginMainClassDetails(File[] listOfPluginJars) {
		Map<URL, String> pluginMainclassMap = new HashMap<URL, String>(listOfPluginJars.length);
		for (File pluginJar : listOfPluginJars) {
			try {
				JarInputStream jarStream = new JarInputStream(new FileInputStream(pluginJar));
				Manifest maniFest = jarStream.getManifest();
				Attributes attributes = maniFest.getMainAttributes();
				String mainClass = attributes.getValue("main-class");
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

	private void loadAndRunMainClassInAllPlugins(Map<URL, String> pluginMainclassMap) throws PluginException {
		Set<URL> urlKeySet = pluginMainclassMap.keySet();
		URL[] urls = new URL[urlKeySet.size()];
		int index = 0;
		for (URL url : urlKeySet) {
			urls[index++] = url;
		}
		if(urlClassLoader == null) {
			urlClassLoader = new URLClassLoader(urls, this.getClass().getClassLoader());
			initilizeAllPlugins(pluginMainclassMap, urlKeySet);
		}
		else {
			throw new PluginException("Plugin Manager Already Loaded... You cannot Load Two times...");
		}
	}

	private void initilizeAllPlugins(Map<URL, String> pluginMainclassMap, Set<URL> urlKeySet) {
		for (URL pluginURL : urlKeySet) {
			try {
				//Create the Plugin Object and Initialized it.
				@SuppressWarnings("unchecked")
				Class<Plugin> forName = (Class<Plugin>) Class.forName(pluginMainclassMap.get(pluginURL), true, urlClassLoader);
				Plugin pluginObject = forName.newInstance();

				//Load the Global Map which will hold the Plugin ID and associated Plugin Object
				pluginList.put(pluginObject.getPluginID(), pluginObject);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	//Singleton Implementation
	private static PluginManagerSPI pluginManagerSPI = null;
	static {
		pluginManagerSPI = new PluginManagerSPI();
	}
	private PluginManagerSPI() {}
	public static PluginManagerSPI getPluginManager() {
		return pluginManagerSPI;
	}

	public static void main(String[] args) throws PluginException {
		PluginManagerSPI spi = PluginManagerSPI.getPluginManager();
		spi.initilize();
		spi.destroy();
	}
	@Override public String getName() {
		return "PluginManagerSPI";
	}
}