package org.or5e.core.plugin.pes;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.or5e.core.plugin.EventMessage;
import org.or5e.core.plugin.PluginEventHandler;

@Sharable
public class PluginServerHandler extends ChannelInboundHandlerAdapter implements
		EventRegistration {
	private Map<String, List<PluginEventHandler>> eventHandlerMap = null;
	public PluginServerHandler() {
		eventHandlerMap = new HashMap<String, List<PluginEventHandler>>();
	}

	@Override public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		ctx.write(msg);
		System.out.println(in.array());
		
	}

	@Override public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override public void registerForEventFromQueue(PluginEventHandler _requestHandler, String... events) {
		for (String evt : events) {
			if (this.eventHandlerMap.containsKey(evt)) {
				this.eventHandlerMap.get(evt).add(_requestHandler);
			} else {
				List<PluginEventHandler> list = new ArrayList<PluginEventHandler>();
				list.add(_requestHandler);
				this.eventHandlerMap.put(evt, list);
			}
		}
	}

	@Override public void raiseEventToQueue(String source, String eventName, Object eventMessage) {
		if (this.eventHandlerMap.containsKey(eventName)) {
			EventMessage evtMessage = new EventMessage();
			evtMessage.setEventData(eventMessage);
			evtMessage.setEventName(eventName);
			evtMessage.setEventSource(source);

			List<PluginEventHandler> listenerList = this.eventHandlerMap.get(eventName);
			for (PluginEventHandler pluginEventHandler : listenerList) {
				pluginEventHandler.eventRaised(evtMessage);
			}
		}
	}
}