package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.SlaveUnitMaster;
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
				su.getSlaveUnitPort()
				) > 0;
	}
	@Override public String[] getSlaveUnitTypeSupported() {
		String[] typeSupported = {"NANO", "UNO", "MCU"};
		return typeSupported;
	}
	@Override public Boolean updateSwitchStatus(String slaveUnitName, Integer switchID, Boolean status) {
		String query = new String(UPDATE_SWITCH_STATUS);
//		SlaveUnitMaster slaveUnit = jdbcTemplate.queryForObject(GET_SLAVE_UNIT_BY_NAME, new SlaveUnitMapper(), slaveUnitName);
		switch(switchID) {
		case 1:
//			slaveUnit.setSw01((status)?1:0);
			query.replaceAll("#id#", "sw01");
			break;
		case 2:
//			slaveUnit.setSw02((status)?1:0);
			query.replaceAll("#id#", "sw02");
			break;
		case 3:
//			slaveUnit.setSw03((status)?1:0);
			query.replaceAll("#id#", "sw03");
			break;
		case 4:
//			slaveUnit.setSw04((status)?1:0);
			query.replaceAll("#id#", "sw04");
			break;
		case 5:
//			slaveUnit.setSw05((status)?1:0);
			query.replaceAll("#id#", "sw05");
			break;
		case 6:
//			slaveUnit.setSw06((status)?1:0);
			query.replaceAll("#id#", "sw06");
			break;
		case 7:
//			slaveUnit.setSw07((status)?1:0);
			query.replaceAll("#id#", "sw07");
			break;
		case 8:
//			slaveUnit.setSw08((status)?1:0);
			query.replaceAll("#id#", "sw08");
			break;
		}
		jdbcTemplate.update(query, (status)?1:0, slaveUnitName);
		return Boolean.TRUE;
	}
	@Override public List<SlaveUnitMaster> getSlaveUnitByMasterUnitID(Integer masterUnitID) {
		return jdbcTemplate.query(GET_SLAVE_UNIT_BY_MCU, new SlaveUnitMapper(), masterUnitID);
	}
}
