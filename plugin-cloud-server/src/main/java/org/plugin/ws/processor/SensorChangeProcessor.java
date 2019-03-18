package org.plugin.ws.processor;

import javax.websocket.Session;

import org.plugin.ws.manager.SessionPoolManager;
import org.plugin.ws.manager.SessionPoolManagerImpl;
import org.plugin.ws.tx.Message;
import org.plugin.ws.tx.MessageType;

public class SensorChangeProcessor implements RequestProcessor {

	@Override public void process(Session session, Message message) {
	}

}
