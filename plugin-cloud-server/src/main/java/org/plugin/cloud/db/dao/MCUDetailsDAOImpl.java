package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.MCUDetails;
import org.plugin.cloud.db.mapper.FDDetailsMapper;
import org.plugin.cloud.db.mapper.MCUMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("mcuDetailsDAOImpl")
public class MCUDetailsDAOImpl implements MCUDetailsDAO, MCUDetailsQuery{
	public JdbcTemplate jdbcTemplate;

	@Override public void addMCUDetails(MCUDetails mcu) {
		
	}

	@Override public void createMCULicense(MCUDetails mcu) {
		
	}

	@Override public void activateMCU(Integer mcuID) {
		
	}

	@Override public void deactivateMCU(Integer mcuID) {
		
	}

	@Override public List<MCUDetails> getAllActiveMCUDetails() {
		return jdbcTemplate.query(QUERY_MCU, new MCUMapper());
	}

	@Override public List<MCUDetails> getAllDeactiveMCUDetails() {
		return jdbcTemplate.query(QUERY_DEACTIVATE_MCU, new MCUMapper());
	}

	@Override public MCUDetails getActiveMCUDetails(Integer mcuID) {
		List<MCUDetails> result = jdbcTemplate.query(QUERY_ACTIVATE_MCU, new MCUMapper());
		return (result.size()>0)? result.get(0):null;
	}
}