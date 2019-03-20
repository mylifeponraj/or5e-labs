package org.plugin.ws.manager;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.plugin.ws.tx.Message;
import org.plugin.ws.tx.MessageType;

public class MCUConnectProcessor implements RequestProcessor {
	@Override public void process(Session session, Message message) {
		Message responseMessage = new Message();
		responseMessage.setMessageFrom("HAServer");
		responseMessage.setMessageTo(message.getMessageFrom());
		responseMessage.setMessageType(MessageType.CNT.name());
		responseMessage.setMessage("REG");
		try {
			session.getBasicRemote().sendObject(responseMessage);
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}
}
