package org.plugin.cloud.request;

public class Expences {
	public Integer expenceID;
	public Integer expenceType;
	public String expenceAmount;
	public String expenceDate;
	public String userID;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Integer getExpenceID() {
		return expenceID;
	}
	public void setExpenceID(Integer expenceID) {
		this.expenceID = expenceID;
	}
	public Integer getExpenceType() {
		return expenceType;
	}
	public void setExpenceType(Integer expenceType) {
		this.expenceType = expenceType;
	}
	public String getExpenceAmount() {
		return expenceAmount;
	}
	public void setExpenceAmount(String expenceAmount) {
		this.expenceAmount = expenceAmount;
	}
	public String getExpenceDate() {
		return expenceDate;
	}
	public void setExpenceDate(String expenceDate) {
		this.expenceDate = expenceDate;
	}
}