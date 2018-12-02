package org.plugin.cloud.db;

import java.util.Calendar;

public class UserMaster {
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public Boolean getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Calendar getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(java.sql.Date lastLoginDate) {
		this.lastLoginDate = DBHelper.getCalendar(lastLoginDate);
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer userID;
	public String userName;
	public String userKey;
	public Boolean userStatus;
	public String userType;
	public Calendar lastLoginDate;
	public String displayName;
	
}
