package org.or5e.node.connector;

import java.io.BufferedReader;
import java.io.IOException;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginLifecycleAdaptor;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class NodeJSConnectorAPI extends PluginLifecycleAdaptor {
	private SocketIOServer _serverSocket;
	private static Integer PORT;
	private Configuration config = new Configuration();
	public NodeJSConnectorAPI() {
		String portOnProperty = getProperties("nodeServerPort");
		if(portOnProperty != null) {
			PORT = Integer.parseInt(portOnProperty);
		}
		else {
			PORT = (Integer)1000;
		}
		//initilize();
	}

	@Override
	public void initilizeService() throws PluginException {
		debug("Initilzing Node JS Connector Module...");
		config.setHostname("localhost");
		config.setPort(PORT);
	}

	@Override
	public void doProcess() {
		this._serverSocket = new SocketIOServer(config);

		_serverSocket.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("client connected !: "+client.toString());
            }
        });
		debug("Node JS Connector Module registering for get data to node...");
		_serverSocket.addEventListener("getDataFromPlugin", String.class, new DataListener<String>() {
            @Override
            public void onData(final SocketIOClient client, String data, final AckRequest ackRequest) {
                info("Get data from plugin");

                System.out.println("Here is the query from the client: \n"+data);

                ackRequest.sendAckData("I am the answer from the Server!");
                client.disconnect();
            }
        });
		debug("Node JS Connector Module registering for push data from node...");
		_serverSocket.addEventListener("sendDataToPlugin", String.class, new DataListener<String>() {
            @Override
            public void onData(final SocketIOClient client, String data, final AckRequest ackRequest) {
                info("Pushing data into plugin");

                System.out.println("Here is the query from the client: \n"+data);

                ackRequest.sendAckData("I am the answer from the Server!");
                client.disconnect();
            }
        });
		debug("Adding disconnect listener...");
		_serverSocket.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("client disconnected !: "+client.toString());
            }
        });
		debug("Starting Node JS Connector Module...");
		this._serverSocket.start();
	}

	@Override
	public void destroyService() {
		debug("Destroying Node JS Connector Module...");
		super.destroyService();
		if(this._serverSocket != null) {
			this._serverSocket.stop();
		}
	}

	@Override
	public String getName() {
		return "NodeJSConnectorAPI";
	}

	@Override
	public String getPluginID() {
		return "NodeJSConnectorAPI";
	}

	public static String inputStreamAsString(BufferedReader bufferedReader) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			sb.append(line + "\n");
		}

		return sb.toString();
	}
	
	public static void main(String[] args) {
		Plugin plugin = new NodeJSConnectorAPI();
		plugin.startPlugin();
		System.out.println("Started server at : "+PORT);
	}
}
