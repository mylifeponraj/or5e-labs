package org.or5e.hm.vo;

import java.util.Calendar;

public class MasterController {
	private int masterUnitID;
	private String masterUnitName;
	private String masterUnitMacID;
	private String masterUnitSoftwareVersion;
	private String masterUnitLicense;
	private String masterUnitStatus;
	private Calendar masterUnitLastLoggedIn;

	public int getMasterUnitID() {
		return masterUnitID;
	}
	public void setMasterUnitID(int masterUnitID) {
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
	public Calendar getMasterUnitLastLoggedIn() {
		return masterUnitLastLoggedIn;
	}
	public void setMasterUnitLastLoggedIn(Calendar masterUnitLastLoggedIn) {
		this.masterUnitLastLoggedIn = masterUnitLastLoggedIn;
	}
}
