package org.plugin.ws.manager;

import javax.websocket.Session;

import org.plugin.ws.session.SessionPoolManager;
import org.plugin.ws.session.SessionPoolManagerImpl;
import org.plugin.ws.tx.Message;
import org.plugin.ws.tx.MessageType;

public class SensorChangeProcessor implements RequestProcessor {

	@Override public void process(Session session, Message message) {
	}

}
