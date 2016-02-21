package org.or5e.ffmpeg.core;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.map.ObjectMapper;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginLifecycleAdaptor;
import org.or5e.ffmpeg.core.job.FFMPEGWorkerThread;

public class BroadcastSystemStatusAndReceiveJobs extends PluginLifecycleAdaptor {
	private String myName;
	private String myIP;
	private NodeInfo nodeInfo;
	private final String INET_ADDR;
	private final Integer PORT;
	@SuppressWarnings("rawtypes")
	private ScheduledFuture scheduledFuture = null;
	private ScheduledExecutorService executorService = null;

	private ServerSocket _serverSocket = null;
	private Integer _serverSocketPort = 80;

	public BroadcastSystemStatusAndReceiveJobs() {
		System.out.println(System.getProperty("java.library.path"));
		this.INET_ADDR = getProperties("ffmpegClusterMulticastAddress");
		this.PORT = Integer.valueOf(getProperties("ffmpegClusterMulticastPort"));
		try {
			this.myName = InetAddress.getLocalHost().getHostName();
			this.myIP = InetAddress.getLocalHost().getHostAddress();
			populateNodeInfo();
			this._serverSocket = new ServerSocket(_serverSocketPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void populateNodeInfo() {
		this.nodeInfo = new NodeInfo();
		this.nodeInfo.setNodeName(this.myName);
		this.nodeInfo.setNodeIP(this.myIP);

		Sigar sigar=new Sigar();
		CpuInfo[] cpuInfoList;
		try {
			cpuInfoList = sigar.getCpuInfoList();
			CpuInfo cpuInfo = cpuInfoList[0];

			this.nodeInfo.setNodeCPUModel(cpuInfo.getModel());
			this.nodeInfo.setNodeCPUMhzSpeed(cpuInfo.getMhz()+" Mhz");
			this.nodeInfo.setNodeCPUCore(cpuInfo.getTotalCores()+"");
			this.nodeInfo.setNodeCPUVendor(cpuInfo.getVendor());
			this.nodeInfo.setNodeOS(ManagementFactory.getOperatingSystemMXBean().getName());
			this.nodeInfo.setNodeOSVersion(ManagementFactory.getOperatingSystemMXBean().getVersion());
		} catch (SigarException e) {
			e.printStackTrace();
		}
		
	}
	@Override public String getName() {
		return "BroadcastSystemStatus";
	}

	@Override public String getPluginID() {
		return getName();
	}

	@Override public void initilize() throws PluginException {
		
	}

	@Override public void doProcess() {
		this.executorService = Executors.newSingleThreadScheduledExecutor();
		this.scheduledFuture = executorService.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					sendAliveSignal();
				} catch (UnknownHostException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		},
		1,
		9,
		TimeUnit.SECONDS);

		while(true) {
			try {
				Socket clientSocket = this._serverSocket.accept();
				new Thread(new FFMPEGWorkerThread(clientSocket, "ClusterMessageReceived"));
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	
	@Override
	public void destroy() {
		this.scheduledFuture.cancel(Boolean.TRUE);
		this.executorService.shutdown();
	}

	public void sendAliveSignal() throws InterruptedException, UnknownHostException {
		System.out.println("Sending HeartBeat to the Network...");
		ObjectMapper mapper = new ObjectMapper();
		// Get the address that we are going to connect to.
		InetAddress addr = InetAddress.getByName(INET_ADDR);

		// Open a new DatagramSocket, which will be used to send the data.
		try (DatagramSocket serverSocket = new DatagramSocket()) {
			String msg = mapper.writeValueAsString(this.nodeInfo);

			DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, PORT);
			serverSocket.send(msgPacket);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException{
		Plugin _plugin = new BroadcastSystemStatusAndReceiveJobs();
		_plugin.startPlugin();
		Thread.sleep(60000);
		_plugin.destroy();
	}
}