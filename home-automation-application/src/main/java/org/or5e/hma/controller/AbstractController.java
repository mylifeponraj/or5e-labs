package org.or5e.hma.controller;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractController<I, O> extends TextWebSocketHandler {
	private Class<I> inputType;
	
	public AbstractController(Class<I> typeI) {
		inputType = typeI;
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		System.out.println("Message Received: "+message.getPayload());
		ObjectMapper mapper = new ObjectMapper();
		try {
			I inputMessage = mapper.readValue(message.getPayload(), inputType);
			O outputMessage = requrestReceived(inputMessage);
			String responseMessagae = mapper.writeValueAsString(outputMessage);
			System.out.println("Message Send: "+responseMessagae);
			session.sendMessage(new TextMessage(responseMessagae));
		}
		catch(Exception exception) {
			exception.printStackTrace();
			session.sendMessage(new TextMessage("Error Happened..."));
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("Connection Established: "+session.getId());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("Something went wrong with Session: "+session.getId());
		exception.printStackTrace();
		session.close();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("Connection Closed: "+session.getId());
	}
	public abstract O requrestReceived(I req);
}
