package org.plugin.cloud.db.dao;

public interface UserMasterQueries {
	public final String GET_ALL_USERS = "select * from usermaster";
	public final String IS_VALID_USER = "select * from usermaster where username=? and userkey=?";
	public final String ACTIVATE_VALID_USER = "update usermaster set userstatus='Y' where userid=?";
	public final String DEACTIVATE_VALID_USER = "update usermaster set userstatus='N' where userid=?";
	public final String ADD_USER = "insert into usermaster (username, userkey, userstatus, usertype, displayname) values (?,?,?,?,?)";
}
