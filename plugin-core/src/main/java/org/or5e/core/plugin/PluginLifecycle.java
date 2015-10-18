package org.or5e.core.plugin;

import org.or5e.core.BaseObject;

public abstract class PluginLifecycle extends BaseObject implements Plugin {
	private Boolean isProcessing = Boolean.FALSE;
	public Boolean isProcessing() {
		return isProcessing;
	}
	public void setIsProcessing(Boolean isProcessing) {
		this.isProcessing = isProcessing;
	}
	public PluginLifecycle() throws PluginException {
		Runtime.getRuntime().addShutdownHook(new Thread(new Closable(), "Shutdown"+getName()));
		initilize();
	}
	public class Closable implements Runnable {
		@Override public void run() {
			while(isProcessing) {
				debug("Thread "+getName()+" is Processing.... Waititing till it Shutdown...");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			destroy();
		}
	}
}