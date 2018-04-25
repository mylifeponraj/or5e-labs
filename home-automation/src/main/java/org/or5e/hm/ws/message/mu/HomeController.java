package org.or5e.hm.ws.message.mu;

public class HomeController {
	public String masterUnitID;
	public String masterUnitName;
	public String masterUnitMacID;
	public String masterUnitIP;
	public String masterUnitSoftwareVersion;
	public String masterUnitLicense;
	public String masterUnitStatus;
	public SlaveUints slaveUints;
	public String getMasterUnitID() {
		return masterUnitID;
	}
	public void setMasterUnitID(String masterUnitID) {
		this.masterUnitID = masterUnitID;
	}
	public String getMasterUnitName() {
		return masterUnitName;
	}
	public void setMasterUnitName(String masterUnitName) {
		this.masterUnitName = masterUnitName;
	}
	public String getMasterUnitMacID() {
		return masterUnitMacID;
	}
	public void setMasterUnitMacID(String masterUnitMacID) {
		this.masterUnitMacID = masterUnitMacID;
	}
	public String getMasterUnitIP() {
		return masterUnitIP;
	}
	public void setMasterUnitIP(String masterUnitIP) {
		this.masterUnitIP = masterUnitIP;
	}
	public String getMasterUnitSoftwareVersion() {
		return masterUnitSoftwareVersion;
	}
	public void setMasterUnitSoftwareVersion(String masterUnitSoftwareVersion) {
		this.masterUnitSoftwareVersion = masterUnitSoftwareVersion;
	}
	public String getMasterUnitLicense() {
		return masterUnitLicense;
	}
	public void setMasterUnitLicense(String masterUnitLicense) {
		this.masterUnitLicense = masterUnitLicense;
	}
	public String getMasterUnitStatus() {
		return masterUnitStatus;
	}
	public void setMasterUnitStatus(String masterUnitStatus) {
		this.masterUnitStatus = masterUnitStatus;
	}
	public SlaveUints getSlaveUints() {
		return slaveUints;
	}
	public void setSlaveUints(SlaveUints slaveUints) {
		this.slaveUints = slaveUints;
	}
}
