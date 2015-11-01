package org.or5e.ffmpeg.cluster.core;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import org.or5e.core.BaseObject;

public class ClusterNodeAvailabilityManager extends BaseObject implements Runnable {
	private List<ClusterNode> _clusterNode = null;
	public ClusterNodeAvailabilityManager(List<ClusterNode> clusterNode) {
		this._clusterNode = clusterNode;
	}
	@Override public void run() {
		for (ClusterNode clusterNode : _clusterNode) {
			try {
				InetAddress clusterNodeInetAddress = InetAddress.getByName(clusterNode.getNodeIP4Address());
				if(!clusterNodeInetAddress.isReachable(1000)) {
					_clusterNode.remove(clusterNode);
				}
			} catch (IOException e) {
				e.printStackTrace();
				_clusterNode.remove(clusterNode);
			}
		}
	}

	@Override public String getName() {
		return "ClusterNodeHeartBeat";
	}
}
