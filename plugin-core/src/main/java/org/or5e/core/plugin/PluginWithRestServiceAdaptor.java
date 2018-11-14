package org.or5e.core.plugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.or5e.core.PluginException;

import static spark.Spark.staticFiles;
import static spark.Spark.options;
import static spark.Spark.before;
import static spark.Spark.port;
import static spark.Spark.threadPool;

public abstract class PluginWithRestServiceAdaptor extends PluginLifecycle {
	static {
		port(1234);
		
		int maxThreads = 8;
		int minThreads = 2;
		int timeOutMillis = 30000;
		threadPool(maxThreads, minThreads, timeOutMillis);
		
		staticFiles.externalLocation(getProperties("sparkStaticPath"));
		options("/*", (request, response) -> {
	        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
	        if (accessControlRequestHeaders != null) {
	            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
	        }

	        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
	        if (accessControlRequestMethod != null) {
	            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
	        }

	        return "OK";
	    });

	    before((request, response) -> {
	        response.header("Access-Control-Allow-Origin", "*");
	        response.header("Access-Control-Request-Method", "*");
	        response.header("Access-Control-Allow-Headers", "*");
	    });
		staticFiles.expireTime(3600);
	}
	@Override public void startPlugin() {
		if(!isProcessing()) {
			setIsProcessing(Boolean.TRUE);
			initilizeService();
		
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			executorService.execute(new Runnable() {
				public void run() {
					Thread.currentThread().setName("Thread-"+getName());
					debug("Running plugin["+getName()+"] in the background");
					doProcess();
					setIsProcessing(Boolean.FALSE);
				}
			});
			executorService.shutdown();
		}
		else {
			throw new PluginException("There is already a process Running on this task... Please Try again Later.");
		}
	}

	@Override public void doProcess() { }
//	@Override public Object processRequestFromStream(String message) {return null;}
	@Override public void destroyService() {}
}
