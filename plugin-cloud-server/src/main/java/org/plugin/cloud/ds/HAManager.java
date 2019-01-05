package org.plugin.cloud.ds;

import org.plugin.cloud.db.MasterControllerMaster;
import org.plugin.cloud.db.SensorUnitMaster;
import org.plugin.cloud.db.SlaveUnitMaster;
import org.plugin.cloud.db.UserMaster;

public interface HAManager {
	public Boolean registerUser(UserMaster userMaster);
	public void addMCU(MasterControllerMaster masterUnit);
	public void addSU(SlaveUnitMaster slaveUnit);
	public void addSensor(SensorUnitMaster sensor);
	public void activateMCU(Integer masterUnitID);
	public void createLicense(String userID);
	public void createMUCode(String masterUnitID);
	public void createSUCode(String slaveUnitID);
}