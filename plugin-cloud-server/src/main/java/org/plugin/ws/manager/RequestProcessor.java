package org.plugin.ws.manager;

import javax.websocket.Session;

import org.plugin.ws.tx.Message;

public interface RequestProcessor {
	public void process(Session session, Message message);
}
