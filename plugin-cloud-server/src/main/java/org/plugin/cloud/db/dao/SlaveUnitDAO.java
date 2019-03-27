package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.SlaveUnitMaster;

public interface SlaveUnitDAO {
	public String[] getSlaveUnitTypeSupported();
	public Boolean addSlaveDetails(SlaveUnitMaster su);
	public Boolean updateSwitchStatus(String slaveUnitName, Integer switchID, Boolean status);
	public List<SlaveUnitMaster> getSlaveUnitByMasterUnitID(Integer masterUnitID);
}
