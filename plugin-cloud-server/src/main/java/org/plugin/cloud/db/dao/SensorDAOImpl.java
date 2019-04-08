package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.SensorUnitMaster;
import org.plugin.cloud.db.request.mapper.SensorUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("sensorDAOImpl")
public class SensorDAOImpl implements SensorDAO, SensorQueries {
	public JdbcTemplate jdbcTemplate;
	
	@Autowired public SensorDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override public List<SensorUnitMaster> getAllSensorUnitMappedToSlaveUnit(Integer slaveUnitID) {
		return jdbcTemplate.query(GET_ALL_SENSOR_FOR_SLAVEUNIT, new SensorUnitMapper(), slaveUnitID);
	}

	public Boolean addSensorUnit(SensorUnitMaster sensorUnitMaster) {
		return (jdbcTemplate.update(INSERT_SENSOR, sensorUnitMaster.getSlaveUnitID(), sensorUnitMaster.getSensorName(), sensorUnitMaster.getSensorDevice(), sensorUnitMaster.getSensorSwitchNo())>0);
	}
}
