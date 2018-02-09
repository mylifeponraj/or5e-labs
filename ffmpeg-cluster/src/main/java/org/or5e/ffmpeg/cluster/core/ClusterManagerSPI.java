package org.or5e.ffmpeg.cluster.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.map.ObjectMapper;
import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginLifecycleAdaptor;
import org.or5e.ffmpeg.core.NodeInfo;

public class ClusterManagerSPI extends PluginLifecycleAdaptor implements ClusterManager {
	private final String shutdownCommand = "Shutdown";
	private final String INET_ADDR;
	private final Integer PORT;
	private MulticastSocket clientSocket = null;
	private byte[] message;
	private InetAddress address;
	private List<ClusterNode> clusterNodeList = new ArrayList<>();
	private Map<Integer, NodeInfo> nodeInfoList = new HashMap<>();
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
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
		scheduler.scheduleAtFixedRate(() -> {checkAllNodesAndUpdate();}, 1, 5, TimeUnit.MINUTES);
		message = new byte[512];
		try {
			address = InetAddress.getByName(INET_ADDR);
			try {
				clientSocket = new MulticastSocket(PORT);
				clientSocket.joinGroup(address);
				ObjectMapper mapper = new ObjectMapper();
				while(true) {
					DatagramPacket msgPacket = new DatagramPacket(message, message.length);
					info("Listening Multicast and waiting for new node.");
					clientSocket.receive(msgPacket);
					info("Message received from new node for ffmpeg cluster.");
					String msg = new String(msgPacket.getData(), 0, msgPacket.getLength());
					NodeInfo info = mapper.readValue(msg, NodeInfo.class);
					debug("ClusterNode: " + info.getNodeName()+":"+info.getNodeIP());
					if(info.getShutdown()) {
						break;
					}
					int key = (info.getNodeName()+info.getNodeIP()).hashCode();
					debug("Adding cluster node.");
					this.nodeInfoList.put(new Integer(key), info);
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
	public List<NodeInfo> getAllClusterNode() {
		List<NodeInfo> nodeInfo = new LinkedList<>();
		Set<Integer> keySet = this.nodeInfoList.keySet();
		for (Integer key : keySet) {
			nodeInfo.add(this.nodeInfoList.get(key));
		}
		return nodeInfo;
	}

	@Override
	public void registerClusterNode() {

	}

	@Override public void shutdownCluster() {
		info("Shutting Down Cluster...");
		scheduler.shutdown();
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
	public void sendMessageToCluster(NodeInfo node, String message) {

	}

	@Override
	public String getName() {
		return "ClusterManagerSPI";
	}
	public void checkAllNodesAndUpdate() {
		this.nodeInfoList.forEach((k,v)-> {if(!checkNode(k, v)) this.nodeInfoList.remove(k);});
	}
	private Boolean checkNode(Integer key, NodeInfo value) {
		try {
			Socket sock = new Socket(value.getNodeName(), 8181);
			DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
			DataInputStream sockIn = new DataInputStream(sock.getInputStream());
			sockOut.writeUTF("You There");
			sockOut.flush();
			String beepResponse = sockIn.readUTF();
			sockIn.close();
			sockOut.close();
			sock.close();
			return (beepResponse.equalsIgnoreCase("Yes"));
		} catch (IOException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	/*	private String generateRandomID() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}*/
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