package org.plugin.cloud.db.dao;

public interface UserMasterQueries {
	public final String GET_ALL_USERS = "select * from usermaster";
	public final String GET_ALL_LIKE_USERS = "select * from usermaster where username like ? and userstatus='Y'";
	public final String GET_ACTIVE_USER = "select * from usermaster where userstatus='Y' and username=?";
	public final String IS_VALID_USER = "select * from usermaster where username=? and userkey=?";
	public final String ACTIVATE_VALID_USER = "update usermaster set userstatus='Y' where username=?";
	public final String DEACTIVATE_VALID_USER = "update usermaster set userstatus='N' where username=?";
	public final String UPDATE_USER_LICENSE = "update usermaster set userLicense=? where username=?";
	public final String ADD_USER = "insert into usermaster (username, userkey, userstatus, usertype, displayname, userEmail, address1, address2, city, state, country, pincode) values (?,?,?,?,?,?,?,?,?,?,?,?)";
}
