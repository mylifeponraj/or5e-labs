package org.plugin.cloud.db.dao;

public interface MCUDetailsQuery {
	public static final String QUERY_MCU = "select * from masterunitcontroller";
	public static final String QUERY_ACTIVATE_MCU = "select * from masterunitcontroller where masterunitstatus='y'";
	public static final String QUERY_DEACTIVATE_MCU = "select * from masterunitcontroller where masterunitstatus='n'";
	public static final String ACTIVATE_MCU = "update masterunitcontroller set masterunitstatus='y' where masterunitid=?";
	public static final String DEACTIVATE_MCU = "update masterunitcontroller set masterunitstatus='n' where masterunitid=?";
	public static final String INSERT_MCU = "insert into masterunitcontroller (masterunitname, masterunitmacid, masterunitsoftwareversion, masterunitstatus, userid, masterunitlicense) values (?,?,?,?,?,?)";
}
