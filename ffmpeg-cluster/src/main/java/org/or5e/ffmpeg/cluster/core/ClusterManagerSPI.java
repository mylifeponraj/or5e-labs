package org.or5e.ffmpeg.cluster.core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginLifecycleAdaptor;

public class ClusterManagerSPI extends PluginLifecycleAdaptor implements ClusterManager {
	private final String shutdownCommand = "Shutdown";
	private final String INET_ADDR;
	private final Integer PORT;
	private MulticastSocket clientSocket = null;
	private byte[] message;
	private InetAddress address;
	private List<ClusterNode> clusterNodeList = new ArrayList<>();

	public ClusterManagerSPI() {
		INET_ADDR = getProperties("ffmpegClusterMulticastAddress");
		PORT = Integer.valueOf(getProperties("ffmpegClusterMulticastPort"));
	}

	@Override
	public String getPluginID() {
		return getName();
	}

	@Override public void initilize() throws PluginException {
	}
	@Override public void doProcess() throws PluginException {
		startListeningToCluster();
	}
	@Override public void destroy() {
		shutdownCluster();
	}

	@Override public void startListeningToCluster() {
		info("Starting Looking for Multicate IP:"+INET_ADDR+" and PORT: "+PORT);
		message = new byte[512];
		try {
			address = InetAddress.getByName(INET_ADDR);
			try {
				clientSocket = new MulticastSocket(PORT);
				clientSocket.joinGroup(address);
				while(true) {
					DatagramPacket msgPacket = new DatagramPacket(message, message.length);
					clientSocket.receive(msgPacket);

					String msg = new String(msgPacket.getData(), 0, msgPacket.getLength());
					System.out.println("Message Received: " + msg);
					if(msg.equals(shutdownCommand)) {
						break;
					}
					StringTokenizer tokens = new StringTokenizer(msg, ",");
					ClusterNode node = new ClusterNode();
					node.setNodeID(generateRandomID());
					node.setNodeName(tokens.nextToken());
					node.setNodeIP4Address(tokens.nextToken());
					clusterNodeList.add(node);
				}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		info("Listening for Node...");
	}

	@Override
	public List<ClusterNode> getAllClusterNode() {
		return this.clusterNodeList;
	}

	@Override
	public void registerClusterNode() {

	}

	@Override public void shutdownCluster() {
		System.out.println("Shutting Down Cluster...");

		try (DatagramSocket serverSocket = new DatagramSocket()){
			InetAddress addr = InetAddress.getByName(INET_ADDR);
			DatagramPacket msgPacket = new DatagramPacket(shutdownCommand.getBytes(), shutdownCommand.getBytes().length, addr, PORT);
			serverSocket.send(msgPacket);
			Thread.sleep(500);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessageToCluster(ClusterNode node, String message) {

	}

	@Override
	public String getName() {
		return "ClusterManagerSPI";
	}
	private String generateRandomID() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Plugin plugin = new ClusterManagerSPI();
		plugin.startPlugin();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Plugin is about to Destory");
		plugin.destroy();
		System.out.println("Main Exiting...");
	}
}