package org.plugin.ws;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.plugin.ws.tx.Message;
import org.plugin.ws.tx.MessageDecoder;
import org.plugin.ws.tx.MessageEncoder;

@ServerEndpoint(value = "/mc", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class MasterControllerConnector {
	@OnOpen public void onOpen(Session session) throws IOException {
		System.out.println("Session Opened...");
	}

	@OnMessage public void onMessage(Session session, Message message) throws IOException {
		System.out.println("Message Received: "+message);
	}

	@OnClose public void onClose(Session session) throws IOException {
		System.out.println("Session Closed...");
	}

	@OnError public void onError(Session session, Throwable throwable) {
		System.out.println("Error and Session Closed...");
	}
}
