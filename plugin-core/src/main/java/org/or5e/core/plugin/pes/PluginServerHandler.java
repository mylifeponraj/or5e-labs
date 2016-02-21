package org.or5e.core.plugin.pes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.event.EventMessage;
import org.or5e.core.plugin.event.EventQueue;
import org.or5e.core.plugin.event.PluginEventQueueSPI;
import org.or5e.core.plugin.event.io.EventConsumer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public class PluginServerHandler extends ChannelInboundHandlerAdapter implements
		EventRegistration {
	private Plugin _plugin;
	private Map<String, List<EventConsumer>> eventHandlerMap = null;
	private List<String> commandToAct = new ArrayList<>();
	public PluginServerHandler(Plugin _plugin) {
		eventHandlerMap = new HashMap<String, List<EventConsumer>>();
		for (String cmd : commandToListen) {
			commandToAct.add(cmd);
		}
		this._plugin = _plugin;
	}
	public static String[] commandToListen = {"Play", "Pause", "Stop", "Seek", "VolUp", "VolDown"};

	@Override public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		ctx.write(msg);
		byte[] bytes = new byte[in.readableBytes()];
		in.readBytes(bytes);

		String commandReceived = new String(bytes);
		StringTokenizer tokens = new StringTokenizer(commandReceived);
		String cmd = tokens.nextToken(" ");
		if(commandToAct.contains(cmd)) {
			String value = null;
			if(tokens.hasMoreTokens()) {
				value = tokens.nextToken();
			}
			EventQueue _queue = PluginEventQueueSPI.getDefaultQueue();
			_queue.injectEventIntoQueue(_plugin, cmd, value);
		}
	}

	@Override public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override public void registerForEventFromQueue(EventConsumer _requestHandler, String... events) {
		for (String evt : events) {
			if (this.eventHandlerMap.containsKey(evt)) {
				this.eventHandlerMap.get(evt).add(_requestHandler);
			} else {
				List<EventConsumer> list = new ArrayList<EventConsumer>();
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

			List<EventConsumer> listenerList = this.eventHandlerMap.get(eventName);
			for (EventConsumer pluginEventHandler : listenerList) {
				pluginEventHandler.listenToEvent(evtMessage);
			}
		}
	}
}