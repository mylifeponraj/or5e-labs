package org.or5e.ffmpeg.cluster.core;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.codehaus.jackson.map.ObjectMapper;
import org.hyperic.sigar.Sigar;
import org.or5e.ffmpeg.core.NodeInfo;

public class MulticastSocketServer {

	final static String INET_ADDR = "224.0.0.3";
	final static int PORT = 8888;

	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		// Get the address that we are going to connect to.
		InetAddress addr = InetAddress.getByName(INET_ADDR);

		// Open a new DatagramSocket, which will be used to send the data.
		try (DatagramSocket serverSocket = new DatagramSocket()) {
			for (int i = 0; i < 1; i++) {

//				InetAddress localHost = InetAddress.getLocalHost();
//				String msg = "FFMPEGNode[" +localHost.getHostName()+","+localHost.getHostAddress()+"]";
//				DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, PORT);
//				serverSocket.send(msgPacket);

				byte[] objectByte = getNodeInfo();
				DatagramPacket msgPacket = new DatagramPacket(objectByte,  objectByte.length, addr, PORT);
				serverSocket.send(msgPacket);

				System.out.println("Server sent packet with msg: " + objectByte);
				Thread.sleep(500);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static byte[] getNodeInfo() throws Exception {
    	NodeInfo info = new NodeInfo();
    	Sigar sigar=new Sigar();
    	InetAddress localHost = InetAddress.getLocalHost();
    	info.setNodeIP(localHost.getHostAddress());
    	info.setNodeName(localHost.getHostName());
    	info.setNodeCPUCore(sigar.getCpuInfoList().length+" Cores");
    	info.setNodeCPUModel(sigar.getCpuInfoList()[0].getModel());
    	info.setNodeCPUVendor(sigar.getCpuInfoList()[0].getVendor());
    	info.setNodeCPUMhzSpeed(sigar.getCpuInfoList()[0].getMhz()+"");
    	info.setNodeOS(System.getProperty("os.name"));
    	info.setNodeOSVersion(System.getProperty("os.version"));
    	info.setShutdown(Boolean.TRUE);
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.writeValueAsBytes(info);
	}

}
