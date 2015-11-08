package org.or5e.ffmpeg.core;

public class NodeInfo {
	public String nodeOS = null;
	public String nodeOSVersion = null;
	public String nodeCPUVendor = null;
	public String nodeCPUModel = null;
	public String nodeCPUCore = null;
	public String nodeCPUMhzSpeed = null;
	public String nodeName = null;
	public String nodeIP = null;
	public String getNodeOS() {
		return nodeOS;
	}
	public void setNodeOS(String nodeOS) {
		this.nodeOS = nodeOS;
	}
	public String getNodeOSVersion() {
		return nodeOSVersion;
	}
	public void setNodeOSVersion(String nodeOSVersion) {
		this.nodeOSVersion = nodeOSVersion;
	}
	public String getNodeCPUVendor() {
		return nodeCPUVendor;
	}
	public void setNodeCPUVendor(String nodeCPUVendor) {
		this.nodeCPUVendor = nodeCPUVendor;
	}
	public String getNodeCPUModel() {
		return nodeCPUModel;
	}
	public void setNodeCPUModel(String nodeCPUModel) {
		this.nodeCPUModel = nodeCPUModel;
	}
	public String getNodeCPUCore() {
		return nodeCPUCore;
	}
	public void setNodeCPUCore(String nodeCPUCore) {
		this.nodeCPUCore = nodeCPUCore;
	}
	public String getNodeCPUMhzSpeed() {
		return nodeCPUMhzSpeed;
	}
	public void setNodeCPUMhzSpeed(String nodeCPUMhzSpeed) {
		this.nodeCPUMhzSpeed = nodeCPUMhzSpeed;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeIP() {
		return nodeIP;
	}
	public void setNodeIP(String nodeIP) {
		this.nodeIP = nodeIP;
	}
	@Override
	public String toString() {
		StringBuffer response = new StringBuffer();
		response.append("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n");
		response.append("Node Name: "+this.nodeName+"\n");
		response.append("Node IP: "+this.nodeIP+"\n");

		response.append("Node OS: "+this.nodeOS+"\n");
		response.append("Node OS Version: "+this.nodeOSVersion+"\n");

		response.append("Node CPU: "+this.nodeCPUModel+"\n");
		response.append("Node CPU Vendor: "+this.nodeCPUVendor+"\n");
		response.append("Node CPU Core: "+this.nodeCPUCore+"\n");
		response.append("Node CPU Speed: "+this.nodeCPUMhzSpeed+"\n");
		response.append("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n");
		return response.toString();
	}
	
}
