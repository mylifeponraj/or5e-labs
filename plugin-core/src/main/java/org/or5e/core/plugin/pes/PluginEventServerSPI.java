package org.or5e.core.plugin.pes;

import org.or5e.core.plugin.EventMessage;
import org.or5e.core.plugin.PluginEventHandler;
import org.or5e.core.plugin.PluginException;
import org.or5e.core.plugin.PluginLifecycleAdaptor;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class PluginEventServerSPI extends PluginLifecycleAdaptor implements PluginEventServer {
	private EventLoopGroup bossGroup = null;
	private EventLoopGroup workerGroup = null;
	private ServerBootstrap serverBootstrap = null;
	private static Integer PORT = null;
	private PluginServerHandler _handler = null;

	@Override public String getName() {
		return "PluginEventServerSPI";
	}

	@Override public String getPluginID() {
		return getName();
	}

	@Override public void initilize() throws PluginException {
		debug("Event Server is Initilizing...");
		this._handler = new PluginServerHandler();
		bossGroup = new NioEventLoopGroup(1);
		workerGroup = new NioEventLoopGroup();
		serverBootstrap = new ServerBootstrap();
		serverBootstrap
			.group(bossGroup, workerGroup)
	        .channel(NioServerSocketChannel.class)
	        .handler(new LoggingHandler(LogLevel.INFO))
	        .childHandler(_handler);
		PORT = Integer.parseInt(getProperties("nettyport"));
		startEventServer();
	}

	@Override public void startEventServer() {
		debug("Event Server is Starting up...");
		try {
			serverBootstrap.bind(PORT).sync().channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override public void stopEventServer() {
		debug("Event Server is Shutting Down...");
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}

	@Override public void registerForEventFromQueue(PluginEventHandler _requestHandler, String... events) {
		_handler.registerForEventFromQueue(_requestHandler, events);
	}

	@Override public void raiseEventToQueue(String source, String eventName, Object eventMessage) {}

	@Override public void doProcess() {
		startEventServer();
	}

	public static void main(String[] args) {
		PluginEventServer serverPlugin = new PluginEventServerSPI();
		serverPlugin.initilize();

		serverPlugin.registerForEventFromQueue(new PluginEventHandler() {
			@Override public void eventRaised(EventMessage message) {
				System.out.println("Media Player Event: "+ message);
			}
		}, "Play", "Pause", "Stop", "Seek", "VolUp", "VolDown");
		serverPlugin.registerForEventFromQueue(new PluginEventHandler() {
			@Override public void eventRaised(EventMessage message) {
				System.out.println("Settings Event: "+ message);
			}
		}, "Settings");

		System.out.println("Main Exitting...");
	}
}