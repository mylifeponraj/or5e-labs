package org.plugin.cloud.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.db.dao.DashboardManagerDAOImpl;
import org.plugin.cloud.request.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/dashboard")
public class DashboardServices {

	@Autowired DashboardManagerDAOImpl dashbaordDAOImpl;

	@GET
	@Path("getExpenceDashboard/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExpenceDashboard(@PathParam("userID") String userID) {
		Dashboard finDashboard = dashbaordDAOImpl.getFinanceDashboard(userID);
		return Response.status(200).entity(finDashboard).build();
	}
}