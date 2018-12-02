package org.plugin.cloud.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.plugin.cloud.db.UserMaster;
import org.springframework.jdbc.core.RowMapper;

public class UserMasterMapper implements RowMapper<UserMaster>{

	@Override public UserMaster mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		UserMaster userMaster = new UserMaster();
		userMaster.setUserID(resultSet.getInt("userid"));
		userMaster.setUserName(resultSet.getString("username"));
		userMaster.setUserKey(resultSet.getString("userkey"));
		userMaster.setUserStatus(resultSet.getString("userStatus").equalsIgnoreCase("y"));
		userMaster.setUserType(resultSet.getString("usertype"));
		userMaster.setLastLoginDate(resultSet.getDate("lastloggedin"));
		userMaster.setDisplayName(resultSet.getString("displayname"));
		return userMaster;
	}
}
