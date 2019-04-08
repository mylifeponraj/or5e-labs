package org.plugin.cloud.request;

import java.util.List;

import org.plugin.cloud.db.MCUDetails;
import org.plugin.cloud.db.UserMaster;

public class HomeAutomationDetails {
	public UserMaster userMaster;
	public MCUDetails mcuDetails;
	public List<SlaveUnitDetails> slaveUnits;

	public UserMaster getUserMaster() {
		return userMaster;
	}
	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}
	public MCUDetails getMcuDetails() {
		return mcuDetails;
	}
	public void setMcuDetails(MCUDetails mcuDetails) {
		this.mcuDetails = mcuDetails;
	}
	public List<SlaveUnitDetails> getSlaveUnits() {
		return slaveUnits;
	}
	public void setSlaveUnits(List<SlaveUnitDetails> slaveUnits) {
		this.slaveUnits = slaveUnits;
	}
}