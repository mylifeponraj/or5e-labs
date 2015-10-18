package org.or5e.core.plugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class PluginLifecycleAdaptor extends PluginLifecycle {
	@Override public void doThreadedProcess() {
		
		if(!isProcessing()) {
			setIsProcessing(Boolean.TRUE);
			
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println("Running in background...");
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
	@Override public Object processRequestFromStream(String message) {return null;}
	@Override public void destroy() {
	}
}