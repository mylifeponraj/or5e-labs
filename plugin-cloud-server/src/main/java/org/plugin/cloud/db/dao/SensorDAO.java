package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.SensorUnitMaster;

public interface SensorDAO {
	public List<SensorUnitMaster> getAllSensorUnitMappedToSlaveUnit(Integer slaveUnitID);
	public Boolean addSensorUnit(SensorUnitMaster sensorUnitMaster);
}
