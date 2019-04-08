package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.SensorUnitMaster;
import org.plugin.cloud.db.SlaveUnitMaster;
import org.plugin.cloud.db.request.mapper.SensorUnitMapper;
import org.plugin.cloud.db.request.mapper.SlaveUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("slvDAOImpl")
public class SlaveUnitDAOImpl implements SlaveUnitDAO, SlaveUnitQueries {
	public JdbcTemplate jdbcTemplate;
	@Autowired
	public SlaveUnitDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override public Boolean addSlaveDetails(SlaveUnitMaster su) {
		return jdbcTemplate.update(
				ADD_SLAVE_UNIT,
				su.getMasterUnitID(),
				su.getSlaveUnitName(),
				su.getSlaveUnitType(),
				su.getSlaveSwitchCnt(),
				su.getSlaveUnitPort(),
				su.getSlaveUnitDisplayName()
				) > 0;
	}
	@Override public String[] getSlaveUnitTypeSupported() {
		String[] typeSupported = {"NANO", "UNO", "MCU"};
		return typeSupported;
	}
	@Override public Boolean updateSwitchStatus(Integer slaveUnitID, Integer switchID, Boolean status, Boolean toggle) {
		String query = new String(UPDATE_SWITCH_STATUS);
		SensorUnitMaster sensorUnit = jdbcTemplate.queryForObject(GET_SWITCH_NUMBER, new SensorUnitMapper(), switchID);
		String col="";
		Integer switchNo = sensorUnit.getSensorSwitchNo();
		switch(switchNo) {
		case 1:
//			slaveUnit.setSw01((status)?1:0);
			query = query.replaceAll("#id#", "sw01");
			col = "sw01";
			break;
		case 2:
//			slaveUnit.setSw02((status)?1:0);
			query = query.replaceAll("#id#", "sw02");
			col = "sw02";
			break;
		case 3:
//			slaveUnit.setSw03((status)?1:0);
			query = query.replaceAll("#id#", "sw03");
			col = "sw03";
			break;
		case 4:
//			slaveUnit.setSw04((status)?1:0);
			query = query.replaceAll("#id#", "sw04");
			col = "sw04";
			break;
		case 5:
//			slaveUnit.setSw05((status)?1:0);
			query = query.replaceAll("#id#", "sw05");
			col = "sw05";
			break;
		case 6:
//			slaveUnit.setSw06((status)?1:0);
			query = query.replaceAll("#id#", "sw06");
			col = "sw06";
			break;
		case 7:
//			slaveUnit.setSw07((status)?1:0);
			query = query.replaceAll("#id#", "sw07");
			col = "sw07";
			break;
		case 8:
//			slaveUnit.setSw08((status)?1:0);
			query = query.replaceAll("#id#", "sw08");
			col = "sw08";
			break;
		}
		if(!toggle) {
			query = query.replaceAll("#val#", status.toString());
			jdbcTemplate.update(query, slaveUnitID);
		}
		else {
			query = query.replaceAll("#val#", "NOT "+col);
			jdbcTemplate.update(query, slaveUnitID);
		}
		return Boolean.TRUE;
	}
	
	@Override public SlaveUnitMaster getSlaveUnitBySlaveUnitName(String slaveUnitName) {
		return jdbcTemplate.queryForObject(GET_SLAVE_UNIT_BY_NAME, new SlaveUnitMapper(), slaveUnitName);
	}
	@Override public List<SlaveUnitMaster> getSlaveUnitByMasterUnitID(Integer masterUnitID) {
		return jdbcTemplate.query(GET_SLAVE_UNIT_BY_MCU, new SlaveUnitMapper(), masterUnitID);
	}
}
