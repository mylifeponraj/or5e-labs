package org.or5e.hm.service;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.or5e.hm.ws.message.Message;

public class UserRegister extends RORHandler {
	private Message response;
	public UserRegister(Message message, Session session) {
		super(message, session);
		response = new Message();
		response.setMessageTo(message.getMessageFrom());
		response.setMessageFrom("CLOUD");
		response.setMessageType("ADD-MERS");
		response.setMessage("Welcome, Added Successfully.");
	}

	@Override public void handleRequest() { 
		if(response.getMessageType().equalsIgnoreCase("ADD-ME")) {
			try {
				session.getBasicRemote().sendObject(response);
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
				return;
			}
			controller.makeUserAvailable(response.getMessageFrom(), session);
			//TODO Update USER EVENT to DB
		}
	}
}