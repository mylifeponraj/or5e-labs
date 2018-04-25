package org.or5e.hm.ws;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.or5e.core.BaseObject;
import org.or5e.hm.service.HeartbeatScheduler;
import org.or5e.hm.sm.MasterUnitController;
import org.or5e.hm.ws.handler.DefaultHandler;
import org.or5e.hm.ws.handler.HeartbeatHandler;
import org.or5e.hm.ws.handler.RORHandler;
import org.or5e.hm.ws.handler.UserRegister;
import org.or5e.hm.ws.message.Message;
import org.or5e.hm.ws.message.MessageDecoder;
import org.or5e.hm.ws.message.MessageEncoder;

@ServerEndpoint(
		value="/hmautomation/{user}",
		decoders = MessageDecoder.class,
		encoders = MessageEncoder.class
		)
public class HomeAutomationWS extends BaseObject{
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
		debug("Open Connection ..."+user);
		this.user = user;
		_controller.addUser(user, session);
	}

	@OnClose
	public void onClose(@PathParam("user") String user, Session session) {
		debug("Close Connection ..."+session.getId());
		_controller.removeUser(user);
	}

	@OnMessage
	public void onMessage(Message message, Session session) {
		debug("Message from the client: " + message + " From: "+session.getId());
		String messageType = message.getMessageType();
		debug("Message Type :"+messageType);
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
		error("User disconnected: "+this.user);
	}

	public static void main(String[] args) {
		new HomeAutomationWS();
	}

	@Override public String getName() {
		return "org.or5e.hm.ws.HomeAutomationWS";
	}
}
