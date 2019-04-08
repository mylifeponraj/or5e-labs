package org.plugin.cloud.rest.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.db.DeviceMaster;
import org.plugin.cloud.db.dao.DeviceMasterDAOImpl;
import org.plugin.cloud.request.Error;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/device")
public class DeviceService {
	@Autowired
	DeviceMasterDAOImpl deviceDAOImpl;

	@GET
	@Path("/getSwitchingDevices")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSwitchingDevices() {
		List<DeviceMaster> allSwitchingDevices = deviceDAOImpl.getAllSwitchingDevices();
		return (allSwitchingDevices != null && allSwitchingDevices.size()>0)? Response.status(200).entity(allSwitchingDevices).build():Response.status(200).entity(new Error("No Data Available.")).build();
	}
	@GET
	@Path("/getSensorDevices")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSensorDevices() {
		List<DeviceMaster> allSensorDevices = deviceDAOImpl.getAllSensorDevices();
		return (allSensorDevices != null && allSensorDevices.size()>0)? Response.status(200).entity(allSensorDevices).build():Response.status(200).entity(new Error("No Data Available.")).build();
	}
}
