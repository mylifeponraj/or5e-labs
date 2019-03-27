package org.plugin.cloud.jmx;

public class HABeanObject implements HABean{
	private static HABeanObject haBean = new HABeanObject();
	private HABeanObject() {}
	public static HABean getHABean() {
		return haBean;
	}
	public boolean isServerRunning() {
		return false;
	}

}
