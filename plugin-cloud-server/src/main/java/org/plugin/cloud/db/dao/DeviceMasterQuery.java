package org.plugin.cloud.db.dao;

public interface DeviceMasterQuery {
	public static final String QUERY_SWITCH_DEVICES = "select * from devicemaster where devicetype='O'";
	public static final String QUERY_SENSOR_DEVICES = "select * from devicemaster where devicetype='I'";
}
