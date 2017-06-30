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
@Table(name = "SensorStatus")
public class SensorStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SENSOR_ST_SQ")
	@SequenceGenerator(name = "SENSOR_ST_SQ", sequenceName = "SENSOR_STATUS_SEQ", allocationSize=1)
	@Column(name = "SensorStatusID")
	private int sensorStatusID;

	@Column(name = "sensorID")
	private int sensorID;

	@Column(name = "SensorDuration")
	private int sensorDuration;

	@Column(name = "SensorOnTime")
	private Timestamp sensorOnTime;

	@Column(name = "SensorOffTime")
	private Timestamp sensorOffTime;

	public int getSensorStatusID() {
		return sensorStatusID;
	}

	public void setSensorStatusID(int sensorStatusID) {
		this.sensorStatusID = sensorStatusID;
	}

	public int getSensorID() {
		return sensorID;
	}

	public void setSensorID(int sensorID) {
		this.sensorID = sensorID;
	}

	public int getSensorDuration() {
		return sensorDuration;
	}

	public void setSensorDuration(int sensorDuration) {
		this.sensorDuration = sensorDuration;
	}

	public Timestamp getSensorOnTime() {
		return sensorOnTime;
	}

	public void setSensorOnTime(Timestamp sensorOnTime) {
		this.sensorOnTime = sensorOnTime;
	}

	public Timestamp getSensorOffTime() {
		return sensorOffTime;
	}

	public void setSensorOffTime(Timestamp sensorOffTime) {
		this.sensorOffTime = sensorOffTime;
	}
}
