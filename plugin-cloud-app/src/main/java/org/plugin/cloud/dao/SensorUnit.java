package org.plugin.cloud.dao;

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
@Table(name = "SensorUnit")
public class SensorUnit {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SENSOR_SQ")
	@SequenceGenerator(name = "SENSOR_SQ", sequenceName = "SENSOR_ID_SEQ", allocationSize=1)
	@Column(name = "SensorID")
	private int sensorID;

	@Column(name = "sensorName")
	private String sensorName;

	@Column(name = "SensorValue")
	private String sensorValue;

	@Column(name = "SensorType")
	private String sensorType;

	@Column(name = "SensorLastChange")
	private Timestamp sensorLastChange;

	@Column(name = "SlaveUnitID")
	private int slaveUnitID;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sensorID")
	private Set<SensorStatus> sensorStatus = new HashSet<>(0);

	public int getSensorID() {
		return sensorID;
	}

	public void setSensorID(int sensorID) {
		this.sensorID = sensorID;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String getSensorValue() {
		return sensorValue;
	}

	public void setSensorValue(String sensorValue) {
		this.sensorValue = sensorValue;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	public Timestamp getSensorLastChange() {
		return sensorLastChange;
	}

	public void setSensorLastChange(Timestamp sensorLastChange) {
		this.sensorLastChange = sensorLastChange;
	}

	public int getSlaveUnitID() {
		return slaveUnitID;
	}

	public void setSlaveUnitID(int slaveUnitID) {
		this.slaveUnitID = slaveUnitID;
	}

	public Set<SensorStatus> getSensorStatus() {
		return sensorStatus;
	}

	public void setSensorStatus(Set<SensorStatus> sensorStatus) {
		this.sensorStatus = sensorStatus;
	}
}