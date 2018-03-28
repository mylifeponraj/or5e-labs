package org.or5e.hm.service;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.or5e.hm.ws.message.Message;

public class SendCommand extends RORHandler {
	public SendCommand(Message message, Session session) {
		super(message, session);
	}
	@Override public void handleRequest() {
		try {
			session.getBasicRemote().sendObject(message);
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}
}