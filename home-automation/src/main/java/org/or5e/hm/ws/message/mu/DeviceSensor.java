package org.or5e.hm.ws.message.mu;

public class DeviceSensor {
	public String sensorID;
	public String sensorName;
	public String sensorValue;
	public String getSensorID() {
		return sensorID;
	}
	public void setSensorID(String sensorID) {
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
}