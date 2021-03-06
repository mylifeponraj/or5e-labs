package org.plugin.cloud.db;

import java.sql.Timestamp;

public class SensorUnitMaster {
	public Integer sensorUnitID;
	public Integer slaveUnitID; //Slave Unit ID
	public Integer sensorDevice; //Device ID
	public String  sensorName;
	public Integer sensorSwitchNo;
	public Integer sensorValue;
	public Timestamp sensorLastChange;
	public Integer getSensorSwitchNo() {
		return sensorSwitchNo;
	}
	public void setSensorSwitchNo(Integer sensorSwitchNo) {
		this.sensorSwitchNo = sensorSwitchNo;
	}
	public Integer getSensorUnitID() {
		return sensorUnitID;
	}
	public void setSensorUnitID(Integer sensorUnitID) {
		this.sensorUnitID = sensorUnitID;
	}
	public Integer getSlaveUnitID() {
		return slaveUnitID;
	}
	public void setSlaveUnitID(Integer slaveUnitID) {
		this.slaveUnitID = slaveUnitID;
	}
	public Integer getSensorDevice() {
		return sensorDevice;
	}
	public void setSensorDevice(Integer sensorDevice) {
		this.sensorDevice = sensorDevice;
	}
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public Integer getSensorValue() {
		return sensorValue;
	}
	public void setSensorValue(Integer sensorValue) {
		this.sensorValue = sensorValue;
	}
	public Timestamp getSensorLastChange() {
		return sensorLastChange;
	}
	public void setSensorLastChange(Timestamp sensorLastChange) {
		this.sensorLastChange = sensorLastChange;
	}
}
