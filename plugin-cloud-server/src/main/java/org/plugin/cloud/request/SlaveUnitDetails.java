package org.plugin.cloud.request;

import java.util.LinkedList;
import java.util.List;

import org.plugin.cloud.db.SensorUnitMaster;
import org.plugin.cloud.db.SlaveUnitMaster;

public class SlaveUnitDetails {
	public Integer slaveUnitID;
	public Integer masterUnitID;
	public String slaveUnitName;
	public String slaveUnitDisplayName;
	public String slaveUnitType;
	public Integer slaveSwitchCnt;
	public String slaveUnitPort;
	public Boolean sw01;
	public Boolean sw02;
	public Boolean sw03;
	public Boolean sw04;
	public Boolean sw05;
	public Boolean sw06;
	public Boolean sw07;
	public Boolean sw08;
	public List<SensorUnitMaster> sensors = new LinkedList<SensorUnitMaster>();
	
	public List<SensorUnitMaster> getSensors() {
		return sensors;
	}
	public void addSensors(SensorUnitMaster sensor) {
		this.sensors.add(sensor);
	}
	public Integer getSlaveSwitchCnt() {
		return slaveSwitchCnt;
	}
	public void setSlaveSwitchCnt(Integer slaveswitchcnt) {
		this.slaveSwitchCnt = slaveswitchcnt;
	}
	public String getSlaveUnitPort() {
		return slaveUnitPort;
	}
	public void setSlaveUnitPort(String slaveUnitPort) {
		this.slaveUnitPort = slaveUnitPort;
	}
	public Integer getSlaveUnitID() {
		return slaveUnitID;
	}
	public void setSlaveUnitID(Integer slaveUnitID) {
		this.slaveUnitID = slaveUnitID;
	}
	public Integer getMasterUnitID() {
		return masterUnitID;
	}
	public void setMasterUnitID(Integer masterUnitID) {
		this.masterUnitID = masterUnitID;
	}
	public String getSlaveUnitName() {
		return slaveUnitName;
	}
	public void setSlaveUnitName(String slaveUnitName) {
		this.slaveUnitName = slaveUnitName;
	}
	public String getSlaveUnitType() {
		return slaveUnitType;
	}
	public void setSlaveUnitType(String slaveUnitType) {
		this.slaveUnitType = slaveUnitType;
	}
	public String getSlaveUnitDisplayName() {
		return (slaveUnitDisplayName==null)?"":slaveUnitDisplayName;
	}
	public void setSlaveUnitDisplayName(String slaveUnitDisplayName) {
		this.slaveUnitDisplayName = slaveUnitDisplayName;
	}
	public Boolean getSw01() {
		return sw01;
	}
	public void setSw01(Boolean sw01) {
		this.sw01 = sw01;
	}
	public Boolean getSw02() {
		return sw02;
	}
	public void setSw02(Boolean sw02) {
		this.sw02 = sw02;
	}
	public Boolean getSw03() {
		return sw03;
	}
	public void setSw03(Boolean sw03) {
		this.sw03 = sw03;
	}
	public Boolean getSw04() {
		return sw04;
	}
	public void setSw04(Boolean sw04) {
		this.sw04 = sw04;
	}
	public Boolean getSw05() {
		return sw05;
	}
	public void setSw05(Boolean sw05) {
		this.sw05 = sw05;
	}
	public Boolean getSw06() {
		return sw06;
	}
	public void setSw06(Boolean sw06) {
		this.sw06 = sw06;
	}
	public Boolean getSw07() {
		return sw07;
	}
	public void setSw07(Boolean sw07) {
		this.sw07 = sw07;
	}
	public Boolean getSw08() {
		return sw08;
	}
	public void setSw08(Boolean sw08) {
		this.sw08 = sw08;
	}
	public void mapSlaveUnitMaster(SlaveUnitMaster slaveUnit) {
		this.masterUnitID = slaveUnit.getMasterUnitID();
		this.slaveSwitchCnt = slaveUnit.getSlaveSwitchCnt();
		this.slaveUnitDisplayName = slaveUnit.getSlaveUnitDisplayName();
		this.slaveUnitID = slaveUnit.getSlaveUnitID();
		this.slaveUnitName = slaveUnit.getSlaveUnitName();
		this.slaveUnitPort = slaveUnit.getSlaveUnitPort();
		this.slaveUnitType = slaveUnit.getSlaveUnitType();
		this.sw01 = slaveUnit.getSw01();
		this.sw02 = slaveUnit.getSw02();
		this.sw03 = slaveUnit.getSw03();
		this.sw04 = slaveUnit.getSw04();
		this.sw05 = slaveUnit.getSw05();
		this.sw06 = slaveUnit.getSw06();
		this.sw07 = slaveUnit.getSw07();
		this.sw08 = slaveUnit.getSw08();
	}
}
