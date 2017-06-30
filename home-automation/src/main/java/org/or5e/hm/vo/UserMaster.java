package org.or5e.hm.vo;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class UserMaster {
	private int userID;
	private String userName;
	private String userKey;
	private String UserStatus;
	private String userType;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private Calendar lastLoggedIn;

	private Set<MasterController> listOfMasterController = new HashSet<MasterController>(0);
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
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
	public String getUserStatus() {
		return UserStatus;
	}
	public void setUserStatus(String userStatus) {
		UserStatus = userStatus;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public Calendar getLastLoggedIn() {
		return lastLoggedIn;
	}
	public Timestamp getLastLoggedInTimestamp() {
		return new Timestamp(this.lastLoggedIn.getTimeInMillis());
	}
	public void setLastLoggedIn(Timestamp lastLoggedIn) {
		this.lastLoggedIn = Calendar.getInstance();
		this.lastLoggedIn.setTimeInMillis(lastLoggedIn.getTime());
	}
	public void setLastLoggedIn(Calendar lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}
}