package org.plugin.cloud.db.request.mapper;

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
		userMaster.setUserEmail(resultSet.getString("useremail"));
		userMaster.setAddress1(resultSet.getString("address1"));
		userMaster.setAddress2(resultSet.getString("address2"));
		userMaster.setCity(resultSet.getString("city"));
		userMaster.setState(resultSet.getString("state"));
		userMaster.setCountry(resultSet.getString("country"));
		userMaster.setPincode(resultSet.getString("pincode"));
		userMaster.setUserphone(resultSet.getString("userphone"));
		userMaster.setUserLicense(resultSet.getString("userlicense"));
		return userMaster;
	}
}
