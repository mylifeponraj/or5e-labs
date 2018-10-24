package org.plugin.cloud.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.plugin.cloud.app.rest.v1")
public class AppConfiguration {
	static {
		System.out.println("Initilizing modules...");
	}
	public AppConfiguration() {
		System.out.println("Application getting Initilized...");
	}
}
