package org.or5e.hm.ws;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

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
	private Session session;
	private String user;
	private MasterUnitController _controller;
	public HomeAutomationWS() {
		_controller = MasterUnitController.getController();
	}

	@OnOpen
	public void onOpen(@PathParam("user") String user, Session session) {
		System.out.println("Open Connection ..."+user);
		this.user = user;
		this.session = session;
		_controller.addUser(user, session);
	}

	@OnClose
	public void onClose(@PathParam("user") String user, Session session) {
		System.out.println("Close Connection ..."+session.getId());
		_controller.removeUser(user);
	}

	@OnMessage
	public String onMessage(Message message, Session session) {
		System.out.println("Message from the client: " + message + " From: "+session.getId());
		String echoMsg = "Echo from the server : " + message;
		return echoMsg;
	}

	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
		System.out.println("User disconnected: "+this.user);
	}

	public static void main(String[] args) {
		new HomeAutomationWS();
	}
}
