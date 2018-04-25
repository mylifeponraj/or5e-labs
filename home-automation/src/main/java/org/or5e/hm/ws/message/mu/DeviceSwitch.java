package org.or5e.hm.ws.message.mu;

public class DeviceSwitch {
	public String switchID;
	public Integer switchStatus;
	public String switchName;
	public String getSwitchID() {
		return switchID;
	}
	public void setSwitchID(String switchID) {
		this.switchID = switchID;
	}
	public Integer getSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(Integer switchStatus) {
		this.switchStatus = switchStatus;
	}
	public String getSwitchName() {
		return switchName;
	}
	public void setSwitchName(String switchName) {
		this.switchName = switchName;
	}	
}