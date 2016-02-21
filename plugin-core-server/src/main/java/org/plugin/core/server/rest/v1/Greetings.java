package org.plugin.core.server.rest.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greetings")
public class Greetings {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHello(@PathParam("name") String name) {
		return "Welcome :"+name;
	}
}
