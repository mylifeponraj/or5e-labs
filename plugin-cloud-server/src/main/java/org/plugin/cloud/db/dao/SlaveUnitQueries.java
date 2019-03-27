package org.plugin.cloud.db.dao;

public interface SlaveUnitQueries {
	public final String ADD_SLAVE_UNIT = "insert into slaveunit (masterunitid, slaveunitname, slaveunittype, slaveswitchcnt, slaveunitport) values (?,?,?,?,?)";
	public final String GET_SLAVE_UNIT_BY_NAME = "select * from slaveunit where slaveunitname = ?";
	public final String GET_SLAVE_UNIT_BY_MCU = "select * from slaveunit where masterunitid = ?";
	public final String UPDATE_SWITCH_STATUS = "update slaveunit set #id# = ? where slaveunitname=?";
}
