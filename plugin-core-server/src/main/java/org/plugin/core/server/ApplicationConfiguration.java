package org.plugin.core.server;

import org.or5e.core.BaseObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationConfiguration extends BaseObject {
	private static ApplicationContext context = null;
	static {
		context = new ClassPathXmlApplicationContext("beans.xml");
	}
	public Object getBean(String beanName) {
		return context.getBean(beanName);
	}
	@Override public String getName() {
		return "ApplicationConfiguration";
	}
	
}
