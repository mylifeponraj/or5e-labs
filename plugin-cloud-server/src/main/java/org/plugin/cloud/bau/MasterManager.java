package org.plugin.cloud.bau;

import java.util.List;

public interface MasterManager {
	public void updateMasterUnitStatus(Integer masterUnitID, Boolean status);
	public void getMasterUnitDetails(Integer masterUnitID);
	public void isMasterUnitActive(Integer masterUnitID);
	public List<String> getAllSlaveUnitForMasterUnit(Integer masterUnitID);
	public List<String> getAllMasterUnit();
	public List<String> getAllSensors(Integer slaveUnitID);
}