package org.or5e.hm.ws;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/hmautomation")
public class HomeAutomationWS {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Open Connection ..."+session.getId());
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Close Connection ..."+session.getId());
	}

	@OnMessage
	public String onMessage(String message, Session session) {
		System.out.println("Message from the client: " + message + " From: "+session.getId());
		String echoMsg = "Echo from the server : " + message;
		return echoMsg;
	}

	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
}
