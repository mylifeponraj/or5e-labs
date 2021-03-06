package org.or5e.hm.ws.handler;

import java.util.List;

import javax.websocket.Session;

import org.or5e.hm.ws.message.Message;

public class HeartbeatHandler extends RORHandler {
	public HeartbeatHandler(Message message, Session session) {
		super(message, session);
	}
	@Override public void handleRequest() {
		if(message.getMessageType().equalsIgnoreCase("HEARTBEATRS")) {
			List<String> allOnlineUsers = controller.getAllOnlineUsers();
			String user = message.getMessageFrom();
			if(!allOnlineUsers.contains(user)) {
				controller.makeUserAvailable(user, session);
			}
		}
	}

	@Override public String getName() {
		return "org.or5e.hm.service.HeartbeatHandler";
	}
}