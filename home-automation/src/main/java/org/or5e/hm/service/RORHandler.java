package org.or5e.hm.service;

import javax.websocket.Session;

import org.or5e.hm.sm.MasterUnitController;
import org.or5e.hm.ws.message.Message;

public abstract class RORHandler {
	protected Message message;
	protected Session session;
	protected MasterUnitController controller = MasterUnitController.getController();
	public RORHandler(Message message, Session session) {
		this.message = message;
		this.session = session;
	}
	public abstract void handleRequest();
}
