package org.plugin.ws;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.plugin.ws.manager.SessionPoolManagerImpl;
import org.plugin.ws.processor.RequestManager;
import org.plugin.ws.tx.Message;
import org.plugin.ws.tx.MessageDecoder;
import org.plugin.ws.tx.MessageEncoder;
import org.plugin.ws.tx.MessageType;

@ServerEndpoint(value = "/has", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class MasterControllerConnector {
	private ExecutorService executor = Executors.newFixedThreadPool(10);
	public MasterControllerConnector() {
		System.out.println("Initilizing the Websocket for /has");
	}
	@OnOpen public void onOpen(Session session) throws IOException {
		System.out.println("Session Opened...");
		Message message = new Message();
		message.setFrom("HAServer");
		message.setTo(session.getId());
		message.setMessageType(MessageType.MCUEVT.name());
		message.setContent("REG");

		try {
			session.getBasicRemote().sendObject(message);
		} catch (EncodeException e) {
			e.printStackTrace();
		}
	}

	@OnMessage public void onMessage(Session session, Message message) throws IOException {
		System.out.println("Message Received: "+message);
		RequestManager reqProcessor = new RequestManager(message, session);
		executor.execute(reqProcessor);
		
	}

	@OnClose public void onClose(Session session) throws IOException {
		SessionPoolManagerImpl.getSessionPoolManager().deleteSession(session);
		System.out.println("Session Closed...");
	}

	@OnError public void onError(Session session, Throwable throwable) {
		try {
			if(session.isOpen()) {
				Message message = new Message();
				message.setFrom("HAServer");
				message.setTo(session.getId());
				message.setMessageType(MessageType.ERRRES.name());
				message.setContent("Something wrong when you send message. We are closing it.");
				session.getBasicRemote().sendObject(message);
				session.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncodeException e) {
			e.printStackTrace();
		}
		System.out.println("Error and Session Closed...");
	}
}
