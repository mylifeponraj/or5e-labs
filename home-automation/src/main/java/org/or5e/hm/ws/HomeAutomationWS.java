package org.or5e.hm.ws;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.or5e.hm.service.DefaultHandler;
import org.or5e.hm.service.HeartbeatHandler;
import org.or5e.hm.service.HeartbeatScheduler;
import org.or5e.hm.service.RORHandler;
import org.or5e.hm.service.UserRegister;
import org.or5e.hm.sm.MasterUnitController;
import org.or5e.hm.ws.message.Message;
import org.or5e.hm.ws.message.MessageDecoder;
import org.or5e.hm.ws.message.MessageEncoder;

@ServerEndpoint(
		value="/hmautomation/{user}",
		decoders = MessageDecoder.class,
		encoders = MessageEncoder.class
		)
public class HomeAutomationWS {
	private String user;
	private MasterUnitController _controller;
	static {
		HeartbeatScheduler.initilizeHeartBeatSecheduler();
	}
	public HomeAutomationWS() {
		_controller = MasterUnitController.getController();
	}

	@OnOpen
	public void onOpen(@PathParam("user") String user, Session session) {
		System.out.println("Open Connection ..."+user);
		this.user = user;
		_controller.addUser(user, session);
	}

	@OnClose
	public void onClose(@PathParam("user") String user, Session session) {
		System.out.println("Close Connection ..."+session.getId());
		_controller.removeUser(user);
	}

	@OnMessage
	public void onMessage(Message message, Session session) {
		System.out.println("Message from the client: " + message + " From: "+session.getId());
		String messageType = message.getMessageType();
		RORHandler handler = null;
		switch (messageType) {
		case "ADD-MERQ": //Registration - First event while master unit connects.
			handler = new UserRegister(message, session);
			break;
		case "HEARTBEATRS":
			handler = new HeartbeatHandler(message, session);
			System.out.println("User Alive...");
			break;
		default:
			handler = new DefaultHandler(message, session);
			break;
		}
		if(handler != null) handler.handleRequest();
	}

	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
		_controller.removeUser(user);
		System.out.println("User disconnected: "+this.user);
	}

	public static void main(String[] args) {
		new HomeAutomationWS();
	}
}
