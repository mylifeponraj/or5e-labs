package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.MCUDetails;

public interface MCUDetailsDAO {
	public Boolean addMCUDetails(MCUDetails mcu);
	public Boolean createMCULicense(MCUDetails mcu);

	public List<MCUDetails> getAllMCUDetails();
	
	public List<MCUDetails> getAllActiveMCUDetails();
	public List<MCUDetails> getAllDeactiveMCUDetails();
	public MCUDetails getActiveMCUDetails(Integer mcuID);
	public List<MCUDetails> getActiveMCUDetails(String mcuName);
	public MCUDetails getMCUDetailsByName(String mcuName);
	public MCUDetails getMCUDetailsByID(Integer mcuName);

	public void activateMCU(String mcuID);
	public void deactivateMCU(String mcuID);
	public Boolean validateUser(String userName, String license, String ipAddress);
}