package org.plugin.cloud.db;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FDDetailsMaster {
	private Integer fdID;
	private String fdName;
	private String fdNumber;
	private Integer fdAmount;
	private String fdDepositDate;
	private Integer fdUserID;
	private Integer fdMaturityAmount;
	private String fdMaturityDate;
	private String fdStatus;
	
	public String getFdStatus() {
		return fdStatus;
	}
	public void setFdStatus(String fdStatus) {
		this.fdStatus = fdStatus;
	}
	public Integer getFdID() {
		return fdID;
	}
	public void setFdID(Integer fdID) {
		this.fdID = fdID;
	}
	public String getFdName() {
		return fdName;
	}
	public void setFdName(String fdName) {
		this.fdName = fdName;
	}
	public String getFdNumber() {
		return fdNumber;
	}
	public void setFdNumber(String fdNumber) {
		this.fdNumber = fdNumber;
	}
	public Integer getFdAmount() {
		return fdAmount;
	}
	public void setFdAmount(Integer fdAmount) {
		this.fdAmount = fdAmount;
	}
	public String getFdDepositDate() {
		return fdDepositDate;
	}
	public void setFdDepositDate(Date fdDepositDate) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		this.fdDepositDate = dateFormat.format(fdDepositDate);
	}
	public Integer getFdUserID() {
		return fdUserID;
	}
	public void setFdUserID(Integer fdUserID) {
		this.fdUserID = fdUserID;
	}
	public Integer getFdMaturityAmount() {
		return fdMaturityAmount;
	}
	public void setFdMaturityAmount(Integer fdMaturityAmount) {
		this.fdMaturityAmount = fdMaturityAmount;
	}
	public String getFdMaturityDate() {
		return fdMaturityDate;
	}
	public void setFdMaturityDate(Date fdMaturityDate) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		this.fdMaturityDate = dateFormat.format(fdMaturityDate);
	}
}