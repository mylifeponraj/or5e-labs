package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.DeviceMaster;

public interface DeviceMasterDAO {
	public List<DeviceMaster> getAllSwitchingDevices();
	public List<DeviceMaster> getAllSensorDevices();
}
