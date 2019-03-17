package org.plugin.cloud.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.request.MasterUnit;

@Path("/mcu")
public class MasterUnitService {

	@POST
	@Path("/addMCU")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMCUDetails(MasterUnit details) {
		
		return null;
	}
}
