package org.plugin.ws.tx;

public class MCUEvent {
	public String mcuID;
	public String mcuSlaveID;
	public String mcuSwitchID;
	public String mcuSwitchCurrentStatus;
	public String mcuSwitchNewStatus;
	public String getMcuID() {
		return mcuID;
	}
	public void setMcuID(String mcuID) {
		this.mcuID = mcuID;
	}
	public String getMcuSlaveID() {
		return mcuSlaveID;
	}
	public void setMcuSlaveID(String mcuSlaveID) {
		this.mcuSlaveID = mcuSlaveID;
	}
	public String getMcuSwitchID() {
		return mcuSwitchID;
	}
	public void setMcuSwitchID(String mcuSwitchID) {
		this.mcuSwitchID = mcuSwitchID;
	}
	public String getMcuSwitchCurrentStatus() {
		return mcuSwitchCurrentStatus;
	}
	public void setMcuSwitchCurrentStatus(String mcuSwitchCurrentStatus) {
		this.mcuSwitchCurrentStatus = mcuSwitchCurrentStatus;
	}
	public String getMcuSwitchNewStatus() {
		return mcuSwitchNewStatus;
	}
	public void setMcuSwitchNewStatus(String mcuSwitchNewStatus) {
		this.mcuSwitchNewStatus = mcuSwitchNewStatus;
	}
}