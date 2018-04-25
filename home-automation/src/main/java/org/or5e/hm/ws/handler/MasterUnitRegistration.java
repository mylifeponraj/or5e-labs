package org.or5e.hm.ws.handler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.or5e.hm.ws.message.Message;
import org.or5e.hm.ws.message.mu.MasterUnitData;
import org.or5e.hm.ws.message.mu.SlaveUnit;

import com.google.gson.Gson;

public class MasterUnitRegistration extends RORHandler {
	private static MasterUnitData data;
	private Message response;
	public MasterUnitRegistration(Message message, Session session) {
		super(message, session);
		response = new Message();
		response.setMessageFrom("CLOUD");
		response.setMessageTo(message.getMessageFrom());
		response.setMessageType("MUREG-RS");
		message.setMessage("Master Unit Details Added..");
	}

	@Override
	public void handleRequest() {
		try {
			if(message.getMessageType().equals("MUREG-RQ")) {
				String jsonMessage = message.getMessage();
				Gson gson = new Gson();
				data = gson.fromJson(jsonMessage, MasterUnitData.class);
				session.getBasicRemote().sendObject(response);
				
			}
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			BufferedReader inStream = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\HOH\\06-Workspace\\HomeMCU\\data\\HomeDB.json")));
			String data;
			StringBuilder builder = new StringBuilder();
			while((data=inStream.readLine())!= null) {
				builder.append(data.trim());
			}
			inStream.close();
			Gson gson = new Gson();
			MasterUnitData data1 = gson.fromJson(builder.toString(), MasterUnitData.class);
			List<SlaveUnit> slaveUnit = data1.getHomecontroller().getSlaveUints().getSlaveUnit();
			for (SlaveUnit slaveUnit2 : slaveUnit) {
				System.out.println(slaveUnit2.getDeviceSensors().get(0).getSensorName());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override public String getName() {
		return "org.or5e.hm.ws.handler.MasterUnitRegistration";
	}
}