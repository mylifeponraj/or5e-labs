package org.plugin.cloud.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.db.MCUDetails;
import org.plugin.cloud.db.SlaveUnitMaster;
import org.plugin.cloud.db.dao.MCUDetailsDAOImpl;
import org.plugin.cloud.db.dao.SensorDAOImpl;
import org.plugin.cloud.db.dao.SlaveUnitDAOImpl;
import org.plugin.cloud.request.Error;
import org.plugin.cloud.request.SlaveUnit;
import org.plugin.cloud.request.Success;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/su")
public class SlaveUnitService {

	private static List<String> supportedTypes = new ArrayList<>();
	static {
		supportedTypes.add("MCU");
		supportedTypes.add("NANO");
		supportedTypes.add("UNO");
	}
	@Autowired
	SlaveUnitDAOImpl slvDAOImpl;
	
	@Autowired
	MCUDetailsDAOImpl mcuDAOImpl;
	
	@Autowired
	SensorDAOImpl sensorDAOImpl;

	@GET
	@Path("/getSlaveUnitTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSlaveUnitTypeSupported() {
		return Response.status(200).entity(supportedTypes).build();
	}
	@POST
	@Path("/addSU")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMCUDetails(SlaveUnit details) {
		MCUDetails activeMCUDetails = mcuDAOImpl.getMCUDetailsByName(details.getMasterUnitName());

		SlaveUnitMaster sum = new SlaveUnitMaster();
		sum.setMasterUnitID(activeMCUDetails.getMasterUnitId());
		sum.setSlaveSwitchCnt(details.getSlaveSwitchCnt());
		sum.setSlaveUnitPort(details.getSlaveUnitPort());
		sum.setSlaveUnitName(details.getSlaveUnitName());
		sum.setSlaveUnitType(details.getSlaveUnitType());
		sum.setSlaveUnitDisplayName(details.getSlaveUnitDisplayName());
		return (slvDAOImpl.addSlaveDetails(sum))?Response.status(200).entity(new Success("Slave Unit added successfully", "200")).build():Response.status(200).entity(new Error("User is not Valid.")).build();
	}

}
