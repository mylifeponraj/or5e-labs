package org.plugin.ws.session;

import javax.websocket.Session;

import org.plugin.ws.tx.Message;

public interface SessionPoolManager {
	public Boolean addSession(Session session, Message message);
	public void deleteSession(Session session);
	public void sendAdminForMCUChange(Message message);
}
