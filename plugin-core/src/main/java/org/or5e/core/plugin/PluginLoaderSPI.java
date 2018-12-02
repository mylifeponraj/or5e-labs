package org.or5e.core.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static spark.Spark.stop;

import org.or5e.core.BaseObject;
import org.or5e.core.PluginException;
import org.or5e.core.plugin.executors.GetAllMainClassForPlugins;
import org.or5e.core.plugin.executors.LoadAllJarsToClasspath;
import org.or5e.core.plugin.executors.LoadAllPluginByCallingMainClass;
import org.or5e.core.plugin.executors.TaskExecute;
import org.or5e.core.plugin.intent.IntentQueueSPI;
import org.or5e.core.service.v1.RestServiceSPI;

public class PluginLoaderSPI extends BaseObject implements PluginLoader {

	private URLClassLoader urlClassLoader = null;
	private Map<String, Plugin> pluginList = null;
	private static PluginLoaderSPI pluginManagerSPI = null;
	private List<TaskExecute> taskExecutors = null;
	static {
		pluginManagerSPI = new PluginLoaderSPI();
		System.out.println("Initilizing Plugins...");
	}
	@Override public void initilizeService() {
		initilize(null);
	}
	@SuppressWarnings("unchecked")
	@Override public void initilize(PluginEvent event)  throws PluginException{
		pluginList = new HashMap<>();
		taskExecutors = new LinkedList<>();

		taskExecutors.add(new LoadAllJarsToClasspath());
		taskExecutors.add(new GetAllMainClassForPlugins());
		taskExecutors.add(new LoadAllPluginByCallingMainClass());

		debug("Initilization process of all the plugins is started.");

		debug("Initilizing event queue for properties.");
		if(event != null) event.complete("Initilizing all Queue.", 10);
		initilizeEventQueue();

		debug("Initilizing the default Plugins.");
		initilizeDefaultServices();

		debug("Getting all the plugins jar file from the plugin folder.");
		if(event != null) event.complete("Loading all plugins from plugin folder.", 20);
		File[] listOfPluginJars = (File[]) taskExecutors.get(0).executeTask(null);
		//File[] listOfPluginJars = getAllJarFilesInPluginFolder();

		debug("Getting all the main files name from all the jars present in the plugin folder.");
		if(event != null) event.complete("Getting all the main classes.", 30);
		Map<URL, String> pluginMainclassMap =  (Map<URL, String>) taskExecutors.get(1).executeTask(listOfPluginJars);
		//Map<URL, String> pluginMainclassMap = getPluginMainClassDetails(listOfPluginJars);

		debug("Loading all the jar files in the current class loader.");
		if(event != null) event.complete("Loading all the main classes.", 40);
		taskExecutors.get(2).executeTask(pluginMainclassMap);
//		loadAllPluginsInClassloader(pluginMainclassMap);

		debug("Initilizing all the plugins.");
		if(event != null) event.complete("Initilizing all the main classes.", 80);
		initilizeAndRunAllPlugins(pluginMainclassMap);
		
//		debug("Initilizing event queue for properties.");
//		if(event != null) event.complete("Initilizing all Queue.", 100);
//		initilizePropertiesEvent();
		if(event != null) event.complete("Initilizing all the main classes.", 100);
		debug("Initilization process of all the plugins is completed successfully.");
		
//		new RawTestServerSocket().start();
	}
	private void initilizeEventQueue() {
		IntentQueueSPI.getIntentQueue();
	}
//	private void initilizePropertiesEvent() {
//		EventQueue _eventQueue = PluginEventQueueSPI.getDefaultQueue();
//		_eventQueue.listenForEventInQueue(new PropertiesEventProcessor(), "add-properties", "get-properties", "delete-properties");
//	}

	@Override public void destroyService() {
		debug("Destroy plugin loader.");
		if(pluginList != null) {
			Set<String> plugins = pluginList.keySet();
			for (String plugin : plugins) {
				pluginList.get(plugin).destroyService();
			}
			stop();
		}
		debug("Destroy Intent Queue.");
		IntentQueueSPI.getIntentQueue().stopIntentQueue();
	}

	@Override public Map<String, Plugin> listAllPlugin() {
		return this.pluginList;
	}

//	private File[] getAllJarFilesInPluginFolder() {
//		File pluginFolder = new File(getProperties("pluginFolder"));
//		debug("Searching plugins in: "+pluginFolder.getAbsolutePath());
//		File[] listOfPluginJars = pluginFolder.listFiles(new JarFileFilter());
//		return listOfPluginJars;
//	}
//
//	@SuppressWarnings("deprecation")
//	private Map<URL, String> getPluginMainClassDetails(File[] listOfPluginJars) {
//		Map<URL, String> pluginMainclassMap = new HashMap<URL, String>(listOfPluginJars.length);
//		for (File pluginJar : listOfPluginJars) {
//			try {
//				JarInputStream jarStream = new JarInputStream(new FileInputStream(pluginJar));
//				Manifest maniFest = jarStream.getManifest();
//				Attributes attributes = maniFest.getMainAttributes();
//				String mainClass = attributes.getValue("main-class");
//				debug("Main Class["+pluginJar.toURL()+"]: "+mainClass);
//				if(mainClass != null) {
//					pluginMainclassMap.put(pluginJar.toURL(), mainClass);
//				}
//				jarStream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return pluginMainclassMap;
//	}
//
//	private void loadAllPluginsInClassloader(Map<URL, String> pluginMainclassMap) throws PluginException {
//		Set<URL> urlKeySet = pluginMainclassMap.keySet();
//		URL[] urls = new URL[urlKeySet.size()];
//		int index = 0;
//		for (URL url : urlKeySet) {
//			urls[index++] = url;
//		}
//		if(urlClassLoader == null) {
//			urlClassLoader = new URLClassLoader(urls, this.getClass().getClassLoader());
//		}
//		else {
//			throw new PluginException("Plugin Manager Already Loaded... You cannot Load Two times...");
//		}
//	}

	private void initilizeDefaultServices() {
		RestServiceSPI plugin = RestServiceSPI.getRestService();
		plugin.initilizeService();
		
	}
	private void initilizeAndRunAllPlugins(Map<URL, String> pluginMainclassMap) {
		Set<URL> urlKeySet = pluginMainclassMap.keySet();
		for (URL pluginURL : urlKeySet) {
			try {
				//Create the Plugin Object and Initialized it.
				debug("Initilizing plugin by Running Main: "+pluginURL);
				@SuppressWarnings("unchecked")
				Class<Plugin> forName = (Class<Plugin>) Class.forName(pluginMainclassMap.get(pluginURL), true, urlClassLoader);
				Plugin pluginObject;
				try {
					pluginObject = forName.getDeclaredConstructor().newInstance();
					//Load the Global Map which will hold the Plugin ID and associated Plugin Object
					pluginList.put(pluginObject.getPluginID(), pluginObject);
					
					//start plugin will call the initilize...
					pluginObject.startPlugin();
				} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					e.printStackTrace();
					debug("Not able to initilize the plugin: "+forName);
				}


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

	public static void main(String[] args) throws PluginException{
		PluginLoaderSPI spi = PluginLoaderSPI.getPluginManager();
		spi.initilizeService();
		try {
			System.out.println("Enter any key to end: ");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		spi.destroyService();
	}
	@Override public String getName() {
		return "PluginManagerSPI";
	}
}