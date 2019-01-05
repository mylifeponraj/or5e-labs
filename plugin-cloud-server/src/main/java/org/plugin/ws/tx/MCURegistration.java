package org.plugin.ws.tx;

public class MCURegistration {
	public String mcuID;
	public String mcuIP;
	public String mcuMacAddr;
	public String mcuLicense;
	public String message;
	public String getMcuID() {
		return mcuID;
	}
	public void setMcuID(String mcuID) {
		this.mcuID = mcuID;
	}
	public String getMcuIP() {
		return mcuIP;
	}
	public void setMcuIP(String mcuIP) {
		this.mcuIP = mcuIP;
	}
	public String getMcuMacAddr() {
		return mcuMacAddr;
	}
	public void setMcuMacAddr(String mcuMacAddr) {
		this.mcuMacAddr = mcuMacAddr;
	}
	public String getMcuLicense() {
		return mcuLicense;
	}
	public void setMcuLicense(String mcuLicense) {
		this.mcuLicense = mcuLicense;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}