package org.plugin.cloud.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.db.ExpenceType;
import org.plugin.cloud.db.FDDetailsMaster;
import org.plugin.cloud.db.dao.ExpenceDAOImpl;
import org.plugin.cloud.db.dao.ExpenceTypeDAOImpl;
import org.plugin.cloud.request.Error;
import org.plugin.cloud.request.Expences;
import org.plugin.cloud.request.FDDetails;
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

	@POST
	@Path("addFD")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFD(FDDetails fdDetails) {
		expenceDAOImpl.createFDRecord(fdDetails);
		Message message = new Message();
		message.setSuccessMessage("1");
		return Response.status(200).entity(message).build();
	}

	@GET
	@Path("getFDetails/{userID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFDDetails(@PathParam("userID") String userID) {
		List<FDDetailsMaster> allFD = expenceDAOImpl.getAllFD(userID);
		if(allFD != null && allFD.size()>0) {
			return Response.status(200).entity(allFD).build();
		}
		else {
			Message message = new Message();
			message.setErrorMessage("No Record Found");
			return Response.status(500).entity(message).build();
		}
	}
	@GET
	@Path("getAllExpences/{userID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllExpenceDetails(@PathParam("userID") String userID) {
		List<Expences> allExpences = expenceDAOImpl.getAllExpences(userID);
		if(allExpences != null && allExpences.size()>0) {
			return Response.status(200).entity(allExpences).build();
		}
		else {
			Message message = new Message();
			message.setErrorMessage("No Record Found");
			return Response.status(500).entity(message).build();
		}
	}
}
