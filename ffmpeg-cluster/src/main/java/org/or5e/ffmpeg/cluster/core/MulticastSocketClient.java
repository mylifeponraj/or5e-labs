package org.or5e.ffmpeg.cluster.core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import org.codehaus.jackson.map.ObjectMapper;
import org.hyperic.sigar.Sigar;
import org.or5e.ffmpeg.core.NodeInfo;

public class MulticastSocketClient {
    final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;
    public static void main1(String... obj) throws Exception {
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
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.writeValue(System.out, info);
    	System.out.println("CPU: "+info);
    	
    }
    public static void main(String[] args) throws UnknownHostException {
        // Get the address that we are going to connect to.
        InetAddress address = InetAddress.getByName(INET_ADDR);
        
        // Create a buffer of bytes, which will be used to store
        // the incoming bytes containing the information from the server.
        // Since the message is small here, 256 bytes should be enough.
        byte[] buf = new byte[256];
        
        // Create a new Multicast socket (that will allow other sockets/programs
        // to join it as well.
        try (MulticastSocket clientSocket = new MulticastSocket(PORT)){
            //Joint the Multicast group.
            clientSocket.joinGroup(address);
     
            while (true) {
                // Receive the information and print it.
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);

                String msg = new String(msgPacket.getData(), 0, msgPacket.getLength());
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Message Received: " + mapper.readValue(msg, NodeInfo.class));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
