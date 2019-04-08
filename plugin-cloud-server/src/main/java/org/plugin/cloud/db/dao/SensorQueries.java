package org.plugin.cloud.db.dao;

public interface SensorQueries {
	public final static String GET_ALL_SENSOR_FOR_SLAVEUNIT = "select * from sensorunit where slaveunitid=?";
	
	public final static String INSERT_SENSOR = "insert into sensorunit (slaveunitid, sensorname, sensordevice, sensorswitchno) values (?, ?, ?, ?)";
	
}
