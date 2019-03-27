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

		slaveUnit.setSw01(rs.getInt("sw01"));
		slaveUnit.setSw02(rs.getInt("sw02"));
		slaveUnit.setSw03(rs.getInt("sw03"));
		slaveUnit.setSw04(rs.getInt("sw04"));
		slaveUnit.setSw05(rs.getInt("sw05"));
		slaveUnit.setSw06(rs.getInt("sw06"));
		slaveUnit.setSw07(rs.getInt("sw07"));
		slaveUnit.setSw08(rs.getInt("sw08"));

		return slaveUnit;
	}

}
