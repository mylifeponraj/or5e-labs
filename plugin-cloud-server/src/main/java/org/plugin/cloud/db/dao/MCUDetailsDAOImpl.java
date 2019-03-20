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
	@Override public Boolean validateUser(String userName, String license, String ipAddress) {
		List<MCUDetails> isValidUserList = jdbcTemplate.query(VALIDATE_MCU_REG, new MCUMapper(), userName, license);
		if(isValidUserList != null && isValidUserList.size()>0) {
			return (jdbcTemplate.update(MCU_ALLOWED, ipAddress, isValidUserList.get(0).getMasterUnitId()))>0;
		}
		return Boolean.FALSE;
	}
	@Override public Boolean createMCULicense(MCUDetails mcu) {
		return Boolean.FALSE;
	}

	@Override public void activateMCU(String mcuID) {
		jdbcTemplate.update(ACTIVATE_MCU, mcuID);
	}

	@Override public void deactivateMCU(String mcuID) {
		jdbcTemplate.update(DEACTIVATE_MCU, mcuID);
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