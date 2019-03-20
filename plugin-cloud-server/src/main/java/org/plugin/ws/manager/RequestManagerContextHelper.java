package org.plugin.ws.manager;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class RequestManagerContextHelper {
	private static RequestManagerContextHelper _helper;
	private WebApplicationContext webApplicationContext;
	private RequestManagerContextHelper(ServletContext context) {
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
	}
	public static RequestManagerContextHelper getRequestManagerContext(ServletContext context) {
		if(_helper == null) {
			_helper = new RequestManagerContextHelper(context);
		}
		return _helper;
	}
	public static RequestManagerContextHelper getRequestManagerContext() {
		return _helper;
	}
	public Object getBean(String name) {
		return webApplicationContext.getBean(name);
	}
}
