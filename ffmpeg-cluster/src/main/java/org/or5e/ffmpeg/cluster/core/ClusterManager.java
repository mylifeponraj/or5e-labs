package org.or5e.ffmpeg.cluster.core;

import java.util.List;

import org.or5e.ffmpeg.core.NodeInfo;

public interface ClusterManager {
	public void startListeningToCluster();
	public List<NodeInfo> getAllClusterNode();
	public void registerClusterNode();
	public void shutdownCluster();
	public void sendMessageToCluster(NodeInfo node, String message);
}
