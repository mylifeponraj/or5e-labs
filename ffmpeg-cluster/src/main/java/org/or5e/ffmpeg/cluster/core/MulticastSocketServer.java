package org.or5e.ffmpeg.cluster.core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
				InetAddress localHost = InetAddress.getLocalHost();
				String msg = "FFMPEGNode[" +localHost.getHostName()+","+localHost.getHostAddress()+"]";

				DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, PORT);
				serverSocket.send(msgPacket);

				System.out.println("Server sent packet with msg: " + msg);
				Thread.sleep(500);
				NodeInfo info = new NodeInfo();
				info.setNodeName(localHost.getHostName());
				info.setNodeIP(localHost.getHostAddress());
				
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
