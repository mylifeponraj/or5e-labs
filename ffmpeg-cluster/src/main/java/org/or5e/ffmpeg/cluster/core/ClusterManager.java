package org.or5e.ffmpeg.cluster.core;

import java.util.List;

public interface ClusterManager {
	public void startListeningToCluster();
	public List<ClusterNode> getAllClusterNode();
	public void registerClusterNode();
	public void shutdownCluster();
	public void sendMessageToCluster(ClusterNode node, String message);
}
