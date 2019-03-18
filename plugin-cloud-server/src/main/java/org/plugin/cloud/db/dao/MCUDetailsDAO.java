package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.MCUDetails;

public interface MCUDetailsDAO {
	public Boolean addMCUDetails(MCUDetails mcu);
	public Boolean createMCULicense(MCUDetails mcu);

	public List<MCUDetails> getAllActiveMCUDetails();
	public List<MCUDetails> getAllDeactiveMCUDetails();
	public MCUDetails getActiveMCUDetails(Integer mcuID);

	public void activateMCU(Integer mcuID);
	public void deactivateMCU(Integer mcuID);
}