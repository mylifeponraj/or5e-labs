package org.plugin.cloud.app.ws.message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class MessageEncoder implements Encoder.Text<Message> {

	@Override public void init(EndpointConfig config) {
		
	}

	@Override public void destroy() {
		
	}

	@Override public String encode(Message message) throws EncodeException {
		Gson gson = new Gson();
		String json = gson.toJson(message);
		return json;
	}
}