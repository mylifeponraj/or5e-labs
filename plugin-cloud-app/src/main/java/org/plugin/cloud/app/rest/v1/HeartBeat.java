package org.plugin.cloud.app.rest.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/monitor")
public class HeartBeat {

	@GET
	@Path("/hb")
	public Response savePayment()  {
		return Response.status(200).entity("I am Alive !!!").build();
	}
}
