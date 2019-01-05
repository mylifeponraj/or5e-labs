package org.plugin.ws.tx;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class MessageDecoder implements Decoder.Text<Message> {

	private static Gson gson = new Gson();

	@Override
	public Message decode(String txtMessage) throws DecodeException {
		return gson.fromJson(txtMessage, Message.class);
	}

	@Override
	public boolean willDecode(String s) {
		return (s != null);
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
	}

	@Override public void destroy() {
	}
}