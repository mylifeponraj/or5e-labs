package org.plugin.cloud.db.request.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.plugin.cloud.db.DeviceMaster;
import org.springframework.jdbc.core.RowMapper;

public class DeviceManagerMapper implements RowMapper<DeviceMaster>{

	@Override public DeviceMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceMaster response = new DeviceMaster();
		response.setDeviceID(rs.getInt("deviceid"));
		response.setDeviceName(rs.getString("devicename"));
		response.setDevicetype(rs.getString("devicetype"));
		return response;
	}

}
