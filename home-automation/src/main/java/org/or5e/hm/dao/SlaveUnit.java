package org.or5e.hm.dao;

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
@Table(name = "SlaveUnit")
public class SlaveUnit {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SLAVE_SQ")
	@SequenceGenerator(name = "SLAVE_SQ", sequenceName = "SLAVE_ID_SEQ", allocationSize=1)
	@Column(name = "SlaveUnitID")
	private int slaveUnitID;

	@Column(name="SlaveUnitName")
	private String slaveUnitName;

	@Column(name="SlaveUnitType")
	private String SlaveUnitType;

	@Column(name="MasterUnitID")
	private int masterUnitID;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "SlaveUnitID")
	private Set<SensorUnit> sensorList = new HashSet<>(0);

	public int getSlaveUnitID() {
		return slaveUnitID;
	}

	public void setSlaveUnitID(int slaveUnitID) {
		this.slaveUnitID = slaveUnitID;
	}

	public String getSlaveUnitName() {
		return slaveUnitName;
	}

	public void setSlaveUnitName(String slaveUnitName) {
		this.slaveUnitName = slaveUnitName;
	}

	public String getSlaveUnitType() {
		return SlaveUnitType;
	}

	public void setSlaveUnitType(String slaveUnitType) {
		SlaveUnitType = slaveUnitType;
	}

	public int getMasterUnitID() {
		return masterUnitID;
	}

	public void setMasterUnitID(int masterUnitID) {
		this.masterUnitID = masterUnitID;
	}

	public Set<SensorUnit> getSensorList() {
		return sensorList;
	}

	public void setSensorList(Set<SensorUnit> sensorList) {
		this.sensorList = sensorList;
	}
}