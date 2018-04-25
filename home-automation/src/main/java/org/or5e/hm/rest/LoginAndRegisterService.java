package org.or5e.hm.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/user")
public class LoginAndRegisterService {

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_HTML)
	public Response loginUser() {
		ResponseBuilder ok = Response.ok();
		
		return ok.build();
	}

	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_HTML)
	public Response goToRegistrationPage() {
		
		try {
			return Response.seeOther(new URI("")).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
