package org.plugin.cloud.request;

import java.util.Calendar;
import java.util.List;

import org.plugin.cloud.db.FDDetailsMaster;

public class Dashboard {
	public List<FDDetailsMaster> fdDetails;
	public Integer totalFDCount=0;
	public Integer totalFDDeposit=0;
	public Integer totalFDMaturity=0;
	public List<Expences> expenceList;
	public Double totalExpence=0.0;
	public Calendar expenceFrom;
	public Calendar expenceTo;
	public List<FDDetailsMaster> getFdDetails() {
		return fdDetails;
	}
	public void setFdDetails(List<FDDetailsMaster> fdDetails) {
		this.fdDetails = fdDetails;
	}
	public Integer getTotalFDCount() {
		return totalFDCount;
	}
	public void setTotalFDCount(Integer totalFDCount) {
		this.totalFDCount = totalFDCount;
	}
	public Integer getTotalFDDeposit() {
		return totalFDDeposit;
	}
	public void setTotalFDDeposit(Integer totalFDDeposit) {
		this.totalFDDeposit = totalFDDeposit;
	}
	public Integer getTotalFDMaturity() {
		return totalFDMaturity;
	}
	public void setTotalFDMaturity(Integer totalFDMaturity) {
		this.totalFDMaturity = totalFDMaturity;
	}
	public List<Expences> getExpenceList() {
		return expenceList;
	}
	public void setExpenceList(List<Expences> expenceList) {
		this.expenceList = expenceList;
	}
	public Double getTotalExpence() {
		return totalExpence;
	}
	public void setTotalExpence(Double totalExpence) {
		this.totalExpence = totalExpence;
	}
	public Calendar getExpenceFrom() {
		return expenceFrom;
	}
	public void setExpenceFrom(Calendar expenceFrom) {
		this.expenceFrom = expenceFrom;
	}
	public Calendar getExpenceTo() {
		return expenceTo;
	}
	public void setExpenceTo(Calendar expenceTo) {
		this.expenceTo = expenceTo;
	}
}
