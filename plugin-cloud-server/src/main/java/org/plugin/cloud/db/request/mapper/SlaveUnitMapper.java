package org.plugin.cloud.db.request.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.plugin.cloud.db.SlaveUnitMaster;
import org.springframework.jdbc.core.RowMapper;

public class SlaveUnitMapper implements RowMapper<SlaveUnitMaster>{

	@Override public SlaveUnitMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
		SlaveUnitMaster slaveUnit = new SlaveUnitMaster();
		slaveUnit.setMasterUnitID(rs.getInt("masterunitid"));
		slaveUnit.setSlaveUnitID(rs.getInt("slaveunitid"));
		slaveUnit.setSlaveUnitName(rs.getString("slaveunitname"));
		slaveUnit.setSlaveUnitType(rs.getString("slaveunittype"));
		slaveUnit.setSlaveUnitPort(rs.getString("slaveunitport"));
		slaveUnit.setSlaveSwitchCnt(rs.getInt("slaveswitchcnt"));
		slaveUnit.setSlaveUnitDisplayName(rs.getString("slaveunitdisplayname"));

		slaveUnit.setSw01(rs.getBoolean("sw01"));
		slaveUnit.setSw02(rs.getBoolean("sw02"));
		slaveUnit.setSw03(rs.getBoolean("sw03"));
		slaveUnit.setSw04(rs.getBoolean("sw04"));
		slaveUnit.setSw05(rs.getBoolean("sw05"));
		slaveUnit.setSw06(rs.getBoolean("sw06"));
		slaveUnit.setSw07(rs.getBoolean("sw07"));
		slaveUnit.setSw08(rs.getBoolean("sw08"));

		return slaveUnit;
	}

}
