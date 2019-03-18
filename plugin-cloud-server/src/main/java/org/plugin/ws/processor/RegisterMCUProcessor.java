package org.plugin.ws.processor;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.plugin.ws.manager.SessionPoolManagerImpl;
import org.plugin.ws.tx.Message;
import org.plugin.ws.tx.MessageType;

public class RegisterMCUProcessor implements RequestProcessor{
	@Override public void process(Session session, Message message) {
		Message response = new Message();
		response.setFrom("HAServer");
		Boolean addSession = SessionPoolManagerImpl.getSessionPoolManager().addSession(session, message);
		if(addSession) {
			response.setTo(session.getId());
			response.setMessageType(MessageType.MCUEVT.name());
			response.setContent("Welcome!!!");
			try {
				session.getBasicRemote().sendObject(response);
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}
		}
		else {
			response.setTo(session.getId());
			response.setMessageType(MessageType.ERRRES.name());
			response.setContent("Not able to add you.");
			try {
				session.getBasicRemote().sendObject(response);
				session.close();
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}
		}
	}
}
