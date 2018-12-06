package org.plugin.cloud.db;

import java.sql.Timestamp;

public class FDDetailsMaster {
	private Integer fdID;
	private String fdName;
	private String fdNumber;
	private Integer fdAmount;
	private Timestamp fdDepositDate;
	private Integer fdUserID;
	private Integer fdMaturityAmount;
	private Timestamp fdMaturityDate;
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
	public Timestamp getFdDepositDate() {
		return fdDepositDate;
	}
	public void setFdDepositDate(Timestamp fdDepositDate) {
		this.fdDepositDate = fdDepositDate;
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
	public Timestamp getFdMaturityDate() {
		return fdMaturityDate;
	}
	public void setFdMaturityDate(Timestamp fdMaturityDate) {
		this.fdMaturityDate = fdMaturityDate;
	}
}