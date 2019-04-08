package org.plugin.cloud.request;

public class SensorUnit {
	public Integer sensorUnitID;
	public Integer slaveUnitID; //Slave Unit ID
	public Integer sensorDevice; //Device ID
	public String  sensorName;
	public Integer sensorValue;
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
}
