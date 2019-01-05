package org.plugin.ws;

import javax.websocket.Session;

public class MasterUnit {
	public Session session;
	public MasterUnit(Session session, String jsonMessage) {
		this.session = session;
	}
	
}
