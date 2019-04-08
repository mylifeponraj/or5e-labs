package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.DeviceMaster;
import org.plugin.cloud.db.request.mapper.DeviceManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("deviceDAOImpl")
public class DeviceMasterDAOImpl implements DeviceMasterDAO, DeviceMasterQuery {
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public DeviceMasterDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override public List<DeviceMaster> getAllSwitchingDevices() {
		return jdbcTemplate.query(QUERY_SWITCH_DEVICES, new DeviceManagerMapper());
	}

	@Override public List<DeviceMaster> getAllSensorDevices() {
		return jdbcTemplate.query(QUERY_SENSOR_DEVICES, new DeviceManagerMapper());
	}

}
