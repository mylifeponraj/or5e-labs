package org.plugin.cloud.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.plugin.cloud.db.MCUDetails;
import org.springframework.jdbc.core.RowMapper;

public class MCUMapper implements RowMapper<MCUDetails>{
	@Override public MCUDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		MCUDetails mcu = new MCUDetails();
		mcu.setMasterUnitId(rs.getInt("masterunitid"));
		mcu.setMasterUnitIPaddr(rs.getString("masterunitipaddr"));
		mcu.setMasterUnitLicense(rs.getString("masterunitlicense"));
		mcu.setMasterUnitMacID(rs.getString("masterunitmacid"));
		mcu.setMasterUnitName(rs.getString("masterunitname"));
		mcu.setMasterUnitSoftwareVersion(rs.getString("masterunitsoftwareversion"));
		mcu.setMasterUnitStatus(rs.getString("masterunitstatus"));
		mcu.setUserID(rs.getInt("userid"));
		return mcu;
	}
}