package org.plugin.ws.processor;

import javax.websocket.Session;

import org.plugin.ws.tx.Message;

public class RequestManager implements Runnable{
	private Message message;
	private Session session;
	public RequestManager(Message message, Session session) {
		this.message = message;
		this.session = session;
	}
	@Override public void run() {
		RequestProcessor processor;
		switch(message.getMessageType()) {
		case "REG":
			processor = new RegisterMCUProcessor();
			processor.process(session, message);
			break;
		case "SVC":
			processor = new SensorChangeProcessor();
			processor.process(session, message);
			break;
		case "CNT":
			processor = new MCUConnectProcessor();
			processor.process(session, message);
			break;
		case "ERRRES":
			processor = new MCUDisconnectProcessor();
			processor.process(session, message);
			break;
		}
	}
}
