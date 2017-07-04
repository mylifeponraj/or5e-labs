package org.or5e.hma.app;

import org.or5e.hma.controller.HeartbeatService;
import org.or5e.hma.controller.HeartbeatServiceHandler;
import org.or5e.hma.controller.HeartbeatServiceSPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableAutoConfiguration
@EnableWebSocket
@EnableWebMvc
public class HomeAutomationApplication extends SpringBootServletInitializer implements WebSocketConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(HomeAutomationApplication.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(heartbeatWebSocketHandler(), "/or5eha").withSockJS();
	}

	@Bean
	public HeartbeatService getHeartbeatService() {
		return new HeartbeatServiceSPI();
	}

	@Bean
	public WebSocketHandler heartbeatWebSocketHandler() {
		return new HeartbeatServiceHandler(getHeartbeatService());
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/templates/");
		resolver.setSuffix(".html");
		return resolver;
	}
}
