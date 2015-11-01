package org.or5e.ffmpeg.cluster.core;

public class ClusterNode {
	public String nodeID = null;
	public String nodeName = null;
	public String nodeIP4Address = null;
	public String nodeIP6Address = null;
	public String nodeLastSuccessPing = null;
	public Boolean nodeStatus = null;
	public String getNodeID() {
		return nodeID;
	}
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeIP4Address() {
		return nodeIP4Address;
	}
	public void setNodeIP4Address(String nodeIP4Address) {
		this.nodeIP4Address = nodeIP4Address;
	}
	public String getNodeIP6Address() {
		return nodeIP6Address;
	}
	public void setNodeIP6Address(String nodeIP6Address) {
		this.nodeIP6Address = nodeIP6Address;
	}
	public String getNodeLastSuccessPing() {
		return nodeLastSuccessPing;
	}
	public void setNodeLastSuccessPing(String nodeLastSuccessPing) {
		this.nodeLastSuccessPing = nodeLastSuccessPing;
	}
	public Boolean getNodeStatus() {
		return nodeStatus;
	}
	public void setNodeStatus(Boolean nodeStatus) {
		this.nodeStatus = nodeStatus;
	}
}