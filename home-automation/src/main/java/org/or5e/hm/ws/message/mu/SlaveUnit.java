package org.or5e.hm.ws.message.mu;

import java.util.List;

public class SlaveUnit {
	public String roomID;
	public String roomName;
	public String deviceName;
	public List<DeviceSwitch> deviceSwitches;
	public List<DeviceSensor> deviceSensor;

	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public List<DeviceSwitch> getDeviceSwitches() {
		return deviceSwitches;
	}
	public void setDeviceSwitches(List<DeviceSwitch> deviceSwitches) {
		this.deviceSwitches = deviceSwitches;
	}
	public List<DeviceSensor> getDeviceSensors() {
		return deviceSensor;
	}
	public void setDeviceSensors(List<DeviceSensor> deviceSensor) {
		this.deviceSensor = deviceSensor;
	}
}