package org.or5e.hm.dao;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MasterUnitController")
public class MasterController {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MASTER_SEQ")
	@SequenceGenerator(name = "MASTER_SEQ", sequenceName = "MASTER_ID_SEQ", allocationSize=1)
	@Column(name = "MasterUnitID")
	private int masterUnitID;

	@Column(name="MasterUnitName")
	private String masterUnitName;

	@Column(name="MasterUnitMacID")
	private String masterUnitMacID;

	@Column(name="MasterUnitSoftwareVersion")
	private String masterUnitSoftwareVersion;

	@Column(name="MasterUnitLicense")
	private String masterUnitLicense;

	@Column(name="MasterUnitStatus")
	private String masterUnitStatus;

	@Column(name="MasterUnitLastLoggedIn")
	private Timestamp masterUnitLastLoggedIn;

	@Column(name="UserID")
	private int userID;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "masterUnitID")
	private Set<SlaveUnit> slaveUnitList = new HashSet<>(0);

	public int getMasterUnitID() {
		return masterUnitID;
	}

	public void setMasterUnitID(int masterUnitID) {
		this.masterUnitID = masterUnitID;
	}

	public String getMasterUnitName() {
		return masterUnitName;
	}

	public void setMasterUnitName(String masterUnitName) {
		this.masterUnitName = masterUnitName;
	}

	public String getMasterUnitMacID() {
		return masterUnitMacID;
	}

	public void setMasterUnitMacID(String masterUnitMacID) {
		this.masterUnitMacID = masterUnitMacID;
	}

	public String getMasterUnitSoftwareVersion() {
		return masterUnitSoftwareVersion;
	}

	public void setMasterUnitSoftwareVersion(String masterUnitSoftwareVersion) {
		this.masterUnitSoftwareVersion = masterUnitSoftwareVersion;
	}

	public String getMasterUnitLicense() {
		return masterUnitLicense;
	}

	public void setMasterUnitLicense(String masterUnitLicense) {
		this.masterUnitLicense = masterUnitLicense;
	}

	public String getMasterUnitStatus() {
		return masterUnitStatus;
	}

	public void setMasterUnitStatus(String masterUnitStatus) {
		this.masterUnitStatus = masterUnitStatus;
	}

	public Timestamp getMasterUnitLastLoggedIn() {
		return masterUnitLastLoggedIn;
	}

	public void setMasterUnitLastLoggedIn(Timestamp masterUnitLastLoggedIn) {
		this.masterUnitLastLoggedIn = masterUnitLastLoggedIn;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Set<SlaveUnit> getSlaveUnitList() {
		return slaveUnitList;
	}

	public void setSlaveUnitList(Set<SlaveUnit> slaveUnitList) {
		this.slaveUnitList = slaveUnitList;
	}
}