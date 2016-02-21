package org.or5e.core.plugin;


public interface Plugin extends Lifecycle{
	public String getPluginID();

	public void doProcess();
	public void startPlugin();

//	public Object processRequestFromStream(String message);
}
