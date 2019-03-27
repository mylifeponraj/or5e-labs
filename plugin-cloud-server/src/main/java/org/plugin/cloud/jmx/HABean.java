package org.plugin.cloud.jmx;

import javax.management.MXBean;

@MXBean
public interface HABean {
	public boolean isServerRunning();
}
