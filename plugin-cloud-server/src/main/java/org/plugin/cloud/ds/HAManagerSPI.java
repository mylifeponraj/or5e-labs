package org.plugin.cloud.ds;

import org.plugin.cloud.db.MCUDetails;
import org.plugin.cloud.db.SensorUnitMaster;
import org.plugin.cloud.db.SlaveUnitMaster;
import org.plugin.cloud.db.UserMaster;
import org.plugin.cloud.db.dao.UserMasterDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("haManagerSPI")
public class HAManagerSPI implements HAManager {

	@Autowired UserMasterDAOImpl userMasterDAOImpl;
	
	@Override public Boolean registerUser(UserMaster userMaster) {
		return userMasterDAOImpl.createUser(userMaster);
	}

	@Override public void addMCU(MCUDetails masterUnit) {

	}

	@Override public void addSU(SlaveUnitMaster slaveUnit) {

	}

	@Override public void addSensor(SensorUnitMaster sensor) {

	}

	@Override public void activateMCU(Integer masterUnitID) {

	}

	@Override public void createLicense(String userID) {

	}

	@Override public void createMUCode(String masterUnitID) {

	}

	@Override public void createSUCode(String slaveUnitID) {

	}
}