package org.or5e.hm.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SensorHistory")
public class SensorHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SENSOR_HIS_SQ")
	@SequenceGenerator(name = "SENSOR_HIS_SQ", sequenceName = "SENSOR_HISTORY_SEQ", allocationSize=1)
	@Column(name = "SensorHistoryID")
	private int sensorHistoryID;

	@Column(name = "SensorID")
	private int sensorID;

	@Column(name = "SensorOldValue")
	private String sensorOldValue;

	@Column(name = "SensorNewValue")
	private String sensorNewValue;

	@Column(name = "ModifiedByClientID")
	private String modifiedByClientID;

	@Column(name = "ModifiedDate")
	private Timestamp modifiedDate;

	public int getSensorHistoryID() {
		return sensorHistoryID;
	}

	public void setSensorHistoryID(int sensorHistoryID) {
		this.sensorHistoryID = sensorHistoryID;
	}

	public int getSensorID() {
		return sensorID;
	}

	public void setSensorID(int sensorID) {
		this.sensorID = sensorID;
	}

	public String getSensorOldValue() {
		return sensorOldValue;
	}

	public void setSensorOldValue(String sensorOldValue) {
		this.sensorOldValue = sensorOldValue;
	}

	public String getSensorNewValue() {
		return sensorNewValue;
	}

	public void setSensorNewValue(String sensorNewValue) {
		this.sensorNewValue = sensorNewValue;
	}

	public String getModifiedByClientID() {
		return modifiedByClientID;
	}

	public void setModifiedByClientID(String modifiedByClientID) {
		this.modifiedByClientID = modifiedByClientID;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}