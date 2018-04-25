package org.or5e.hm.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/welcome")
public class WelcomeService {
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String sayHello() {
		return "<html><head></html><body><h1>Hello world</h1></body></html>";
	}
}
