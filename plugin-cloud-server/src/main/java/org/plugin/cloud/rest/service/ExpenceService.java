package org.plugin.cloud.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.db.ExpenceType;
import org.plugin.cloud.db.dao.ExpenceDAOImpl;
import org.plugin.cloud.db.dao.ExpenceTypeDAOImpl;
import org.plugin.cloud.request.Error;
import org.plugin.cloud.request.Expences;
import org.plugin.cloud.request.Message;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/expences")
public class ExpenceService {

	@Autowired ExpenceTypeDAOImpl expenceTypeDAOImpl;
	@Autowired ExpenceDAOImpl expenceDAOImpl;

	@GET
	@Path("/getAllExpenceType")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getAllExpenceType() {
		List<ExpenceType> allExpenceType = expenceTypeDAOImpl.getAllExpenceType();
        return (allExpenceType != null) ? Response.status(200).entity(allExpenceType).build():Response.status(200).entity(new Error("Expence Service Failed.")).build();
    }
	@POST
	@Path("addExpence")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExpences(Expences exp) {
		expenceDAOImpl.createExpenceRecord(exp);
		Message message = new Message();
		message.setSuccessMessage("1");
		return Response.status(200).entity(message).build();
	}
}
