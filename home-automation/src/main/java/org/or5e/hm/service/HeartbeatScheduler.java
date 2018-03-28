package org.or5e.hm.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.or5e.hm.sm.MasterUnitController;
import org.or5e.hm.ws.message.Message;

public class HeartbeatScheduler {

	private Timer time = new Timer();
	protected MasterUnitController controller;
	protected Map<String, Object> allUserSessions;
	protected Message message = new Message();
	private static HeartbeatScheduler scheduler = null;

	private HeartbeatScheduler() {
		controller = MasterUnitController.getController();
		allUserSessions = controller.getAllUserSessions();
		message.setMessageFrom("Cloud");
		message.setMessageTo("MasterUnit");
		message.setMessageType("HEARTBEATRQ");
		message.setMessage("Are You Alive!!");
		HeartBeatEvent hbe = new HeartBeatEvent();
		time.schedule(hbe, 0, 5000);
	}
	public static void initilizeHeartBeatSecheduler() {
		if(scheduler == null) scheduler = new HeartbeatScheduler();
	}
	class HeartBeatEvent extends TimerTask {
		private List<String> allOnlineUsers;
		@Override public void run() {
			allOnlineUsers = controller.getAllOnlineUsers();
//			System.out.println("Checking for online Users..."+allOnlineUsers.size());
			for (String user : allOnlineUsers) {
				System.out.println("Checking for user: "+user);
				Object object = allUserSessions.get(user);
				if(object != null) {
					Session userSession = (Session) object;
					if(userSession.isOpen()) {
						try {
							userSession.getBasicRemote().sendObject(message);
						} catch (IOException | EncodeException e) {
							e.printStackTrace();
						}
					}
					else {
						System.out.println("Session not able to find...");
					}
				}
			}
		}

	}
}
