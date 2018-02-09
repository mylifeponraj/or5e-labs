package org.or5e.core.plugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.or5e.core.PluginException;

public abstract class PluginLifecycleAdaptor extends PluginLifecycle {
	@Override public void startPlugin() {
		if(!isProcessing()) {
			setIsProcessing(Boolean.TRUE);
			initilize();
		
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
	@Override public void destroy() {}
}