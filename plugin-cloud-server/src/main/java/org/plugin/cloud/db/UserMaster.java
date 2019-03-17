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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUserLicense() {
		return userLicense;
	}
	public void setUserLicense(String userLicense) {
		this.userLicense = userLicense;
	}

	public Integer userID;
	public String userName;
	public String userKey;
	public Boolean userStatus;
	public String userType;
	public Calendar lastLoginDate;
	public String displayName;
	public String userEmail;
	public String address1;
	public String address2;
	public String city;
	public String state;
	public String country="IN";
	public String pincode;
	public String userphone;
	public String userLicense;
}
