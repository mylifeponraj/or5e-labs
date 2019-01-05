package org.plugin.ws.ds;

public interface HADSManager {
	public MCURegistrationResponse mcuEvent(MCURegistrationRequest regRequet);
	public MCUStatusResponse mcuEvent(MCUStatusRequest statusRequest);
}