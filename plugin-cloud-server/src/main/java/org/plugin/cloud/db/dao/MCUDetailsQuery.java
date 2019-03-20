package org.plugin.cloud.db.dao;

public interface MCUDetailsQuery {
	public static final String QUERY_MCU = "select * from masterunitcontroller";
	public static final String VALIDATE_MCU_REG = "select * from masterunitcontroller where userid=? and masterunitlicense=?";
	public static final String QUERY_ACTIVATE_MCU = "select * from masterunitcontroller where masterunitstatus='y'";
	public static final String QUERY_DEACTIVATE_MCU = "select * from masterunitcontroller where masterunitstatus='n'";
	public static final String MCU_ALLOWED = "update masterunitcontroller set masterunitstatus='y', masterunitipaddr=?, masterunitlastloggedin=current_timestamp where masterunitname=?";
	public static final String ACTIVATE_MCU = "update masterunitcontroller set masterunitstatus='y' where masterunitname=?";
	public static final String DEACTIVATE_MCU = "update masterunitcontroller set masterunitstatus='n' where masterunitname=?";
	public static final String INSERT_MCU = "insert into masterunitcontroller (masterunitname, masterunitmacid, masterunitsoftwareversion, masterunitstatus, userid, masterunitlicense) values (?,?,?,?,?,?)";
}
