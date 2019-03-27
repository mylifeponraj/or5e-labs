package org.plugin.cloud.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.db.MCUDetails;
import org.plugin.cloud.db.dao.MCUDetailsDAOImpl;
import org.plugin.cloud.request.Error;
import org.plugin.cloud.request.MasterUnit;
import org.plugin.cloud.request.Success;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/mcu")
public class MasterUnitService {

	@Autowired
	MCUDetailsDAOImpl mcuDAOImpl;
	
	@POST
	@Path("/addMCU")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMCUDetails(MasterUnit details) {
		MCUDetails mcu = new MCUDetails();
		mcu.setMasterUnitMacID(details.getMasterUnitMacID());
		mcu.setMasterUnitIPaddr(details.getMasterUnitIPaddr());
		mcu.setMasterUnitLicense(details.getMasterUnitLicense());
		mcu.setMasterUnitId(details.getMasterUnitId());
		mcu.setMasterUnitName(details.getMasterUnitName());
		mcu.setMasterUnitSoftwareVersion(details.getMasterUnitSoftwareVersion());
		mcu.setMasterUnitStatus(details.getMasterUnitStatus());
		mcu.setUserID(details.getUserID());

		return (mcuDAOImpl.addMCUDetails(mcu))?Response.status(200).entity(new Success("Master Controller added successfully", "200")).build():Response.status(200).entity(new Error("User is not Valid.")).build();
	}
	
	@GET
	@Path("/getMCU/{masterUnitName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMCUDetails(@PathParam("masterUnitName") String masterUnitName) {
		List<MCUDetails> activeMCUDetails = mcuDAOImpl.getActiveMCUDetails(masterUnitName);
		List<String> response = new ArrayList<>();
		for (MCUDetails mcuDetails : activeMCUDetails) {
			response.add(mcuDetails.getMasterUnitName());
		}
		return (response.size()>0)?
				Response.status(200).entity(response).build():
				Response.status(200).entity(new Error("No MasterUnit Available.")).build();
	}
	
	@GET
	@Path("/getAllMCU")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getALLMCUDetails() {
		List<MCUDetails> allActiveMCUDetails = mcuDAOImpl.getAllMCUDetails();
		return (allActiveMCUDetails.size()>0)?Response.status(200).entity(allActiveMCUDetails).build():Response.status(200).entity(new Error("No Master Unit Availablit.")).build();
	}
}
