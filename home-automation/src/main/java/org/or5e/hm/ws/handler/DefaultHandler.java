package org.or5e.hm.ws.handler;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.or5e.hm.ws.message.Message;

public class DefaultHandler extends RORHandler {
	private Message response;
	public DefaultHandler(Message message, Session session) {
		super(message, session);
		response = new Message();
		response.setMessageFrom("CLOUD");
		response.setMessage("Welcome, I do not know you");
		response.setMessageType("DEFAULT");
	}

	@Override public void handleRequest() {
		try {
			session.getBasicRemote().sendObject(response);
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}

	@Override public String getName() {
		return "org.or5e.hm.service.DefaultHandler";
	}
}
