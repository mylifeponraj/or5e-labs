package org.or5e.hm.ws.message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class MessageDecoder implements Decoder.Text<Message> {

	@Override public void init(EndpointConfig config) {
		
	}

	@Override public void destroy() {
		
	}

	@Override public Message decode(String messageFromClient) throws DecodeException {
		Gson gson = new Gson();
		Message message = gson.fromJson(messageFromClient, Message.class);
		return message;
	}

	@Override public boolean willDecode(String message) {
		return (message != null);
	}
}
