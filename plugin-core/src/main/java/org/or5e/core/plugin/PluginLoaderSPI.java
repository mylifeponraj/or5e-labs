package org.or5e.core.plugin;

import java.io.File;
import java.io.FileInputStream;
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
import org.or5e.core.filefilter.JarFileFilter;

public class PluginLoaderSPI extends BaseObject implements PluginLoader {

	private URLClassLoader urlClassLoader = null;
	private Map<String, Plugin> pluginList = null;
	private static PluginLoaderSPI pluginManagerSPI = null;
	static {
		pluginManagerSPI = new PluginLoaderSPI();
		pluginManagerSPI.initilize();
	}
	@Override public void initilize()  throws PluginException{
		pluginList = new HashMap<>();

		debug("Initilization process of all the plugins is started.");

		debug("Getting all the plugins jar file from the plugin folder.");
		File[] listOfPluginJars = getAllJarFilesInPluginFolder();

		debug("Getting all the main files name from all the jars present in the plugin folder.");
		Map<URL, String> pluginMainclassMap = getPluginMainClassDetails(listOfPluginJars);

		debug("Loading all the jar files in the current class loader.");
		loadAllPluginsInClassloader(pluginMainclassMap);

		debug("Initilizing all the plugins.");
		initilizeAndRunAllPlugins(pluginMainclassMap);

		debug("Initilization process of all the plugins is completed successfully.");
	}

	@Override public void destroy() {
		debug("Destroy plugin loader.");
		Set<String> plugins = pluginList.keySet();
		for (String plugin : plugins) {
			pluginList.get(plugin).destroy();
		}
	}

	@Override public Map<String, Plugin> listAllPlugin() {
		return this.pluginList;
	}

	private File[] getAllJarFilesInPluginFolder() {
		File pluginFolder = new File(getProperties("pluginFolder"));
		File[] listOfPluginJars = pluginFolder.listFiles(new JarFileFilter());
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

	private void loadAllPluginsInClassloader(Map<URL, String> pluginMainclassMap) throws PluginException {
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
	}

	private void initilizeAndRunAllPlugins(Map<URL, String> pluginMainclassMap) {
		Set<URL> urlKeySet = pluginMainclassMap.keySet();
		for (URL pluginURL : urlKeySet) {
			try {
				//Create the Plugin Object and Initialized it.
				@SuppressWarnings("unchecked")
				Class<Plugin> forName = (Class<Plugin>) Class.forName(pluginMainclassMap.get(pluginURL), true, urlClassLoader);
				Plugin pluginObject = forName.newInstance();
				//Load the Global Map which will hold the Plugin ID and associated Plugin Object
				pluginList.put(pluginObject.getPluginID(), pluginObject);

				//start plugin will call the initilize...
				//pluginObject.initilize();
				pluginObject.startPlugin();

			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	//Singleton Implementation
	private PluginLoaderSPI() {}
	public static PluginLoaderSPI getPluginManager() {
		return pluginManagerSPI;
	}

	public static void main(String[] args) throws PluginException, InterruptedException {
//		PluginLoaderSPI spi = PluginLoaderSPI.getPluginManager();
//		spi.initilize();
//		Thread.sleep(1000);
//		spi.destroy();
	}
	@Override public String getName() {
		return "PluginManagerSPI";
	}
}