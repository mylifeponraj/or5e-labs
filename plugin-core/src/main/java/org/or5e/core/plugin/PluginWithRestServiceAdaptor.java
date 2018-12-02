package org.or5e.core.plugin;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.threadPool;

import org.or5e.core.PluginException;

public abstract class PluginWithRestServiceAdaptor extends PluginLifecycle {
	static {
		port(Integer.parseInt(getProperties("spark.port")));
		threadPool(Integer.parseInt(getProperties("spark.minThread")), Integer.parseInt(getProperties("spark.maxThread")), 30000);
		staticFiles.externalLocation(getProperties("sparkStaticPath"));
		staticFiles.expireTime(3600);
	}
	@Override public void startPlugin() {
		if(!isProcessing()) {
			setIsProcessing(Boolean.TRUE);
			initilizeService();
		}
		else {
			throw new PluginException("There is already a process Running on this task... Please Try again Later.");
		}
	}

	@Override public void doProcess() { }
//	@Override public Object processRequestFromStream(String message) {return null;}
	@Override public void destroyService() {}
}
