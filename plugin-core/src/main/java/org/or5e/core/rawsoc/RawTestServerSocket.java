package org.or5e.core.rawsoc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.or5e.core.plugin.intent.Intent;
import org.or5e.core.plugin.intent.IntentQueueSPI;

public class RawTestServerSocket extends Thread {
	private ServerSocket _sock = null;
	private Integer PORT = 1234;
	public RawTestServerSocket() {
		try {
			this.setName("Server Socket");
			this._sock = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override public void run () {
		System.out.println("Started Listening to client Connection..");
		try {
			Socket sock = this._sock.accept();
			DataInputStream inStream=new DataInputStream(sock.getInputStream());
			DataOutputStream outStream=new DataOutputStream(sock.getOutputStream());
			outStream.writeUTF("Welcome...\n\r");
			String command = "";
			while(!command.equalsIgnoreCase("EXIT")) {
				outStream.writeUTF("Enter Your Command: ");
				command=(String) inStream.readLine();
				System.out.println("Command recieved: "+command);
					Intent intent = new Intent(command, "Command: "+command);
					IntentQueueSPI.getIntentQueue().raiseIntentToDefaultQueue(command, intent);
			}
			outStream.close();
			inStream.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	public static void main(String[] args) {
		new RawTestServerSocket().start();
	}
}
