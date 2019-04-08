package org.plugin.cloud.db.request.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.plugin.cloud.db.SensorUnitMaster;
import org.springframework.jdbc.core.RowMapper;

public class SensorUnitMapper implements RowMapper<SensorUnitMaster>{

	@Override public SensorUnitMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
		SensorUnitMaster sensorUnit = new SensorUnitMaster();

		sensorUnit.setSensorUnitID(rs.getInt("sensorid"));
		sensorUnit.setSlaveUnitID(rs.getInt("slaveunitid"));
		sensorUnit.setSensorDevice(rs.getInt("sensordevice"));
		
		sensorUnit.setSensorName(rs.getString("sensorname"));
		sensorUnit.setSensorValue(rs.getInt("sensorvalue"));
		sensorUnit.setSensorLastChange(rs.getTimestamp("sensorlastchange"));
		sensorUnit.setSensorSwitchNo(rs.getInt("sensorswitchno"));
		return sensorUnit;
	}

}
