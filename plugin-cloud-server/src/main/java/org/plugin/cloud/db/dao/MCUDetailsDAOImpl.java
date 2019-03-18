package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.MCUDetails;
import org.plugin.cloud.db.request.mapper.MCUMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("mcuDetailsDAOImpl")
public class MCUDetailsDAOImpl implements MCUDetailsDAO, MCUDetailsQuery{
	public JdbcTemplate jdbcTemplate;
	@Autowired
	public MCUDetailsDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override public Boolean addMCUDetails(MCUDetails mcu) {
		return jdbcTemplate.update(
				INSERT_MCU,
				mcu.getMasterUnitName(),
				mcu.getMasterUnitMacID(),
				mcu.getMasterUnitSoftwareVersion(),
				((mcu.getMasterUnitStatus() == null)? "n":"y"),
				mcu.getUserID(),
				mcu.getMasterUnitLicense()
				) > 0;
	}

	@Override public Boolean createMCULicense(MCUDetails mcu) {
		return Boolean.FALSE;
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