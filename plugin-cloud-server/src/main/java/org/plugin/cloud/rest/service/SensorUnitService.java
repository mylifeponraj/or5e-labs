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

import org.plugin.cloud.db.SensorUnitMaster;
import org.plugin.cloud.db.SlaveUnitMaster;
import org.plugin.cloud.db.dao.DeviceMasterDAOImpl;
import org.plugin.cloud.db.dao.SensorDAOImpl;
import org.plugin.cloud.db.dao.SlaveUnitDAOImpl;
import org.plugin.cloud.request.Success;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/sensor")
public class SensorUnitService {

	@Autowired
	SlaveUnitDAOImpl slaveDAOImpl;
	
	@Autowired
	SensorDAOImpl sensorDAOImpl;

	@Autowired
	DeviceMasterDAOImpl deviceDAOImpl;
	
	@GET
	@Path("/getSensorUnits/{slaveUnitName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSensorUnits(@PathParam("slaveUnitName") String slaveUnitName) {
		SlaveUnitMaster slaveUnit = slaveDAOImpl.getSlaveUnitBySlaveUnitName(slaveUnitName);
		List<SensorUnitMaster> allSensorsForSlaveUnit = sensorDAOImpl.getAllSensorUnitMappedToSlaveUnit(slaveUnit.getSlaveUnitID());
		return (allSensorsForSlaveUnit != null && allSensorsForSlaveUnit.size()>0)?Response.status(200).entity(allSensorsForSlaveUnit).build():Response.status(200).entity(new Error("No Sensor Device Available")).build();
	}
	@POST
	@Path("/addSensorUnits/{slaveUnitName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSensorsForSlaveUnit(@PathParam("slaveUnitName") String  slaveUnitName, List<SensorUnitMaster> sensorUnits) {
		SlaveUnitMaster slaveUnit = slaveDAOImpl.getSlaveUnitBySlaveUnitName(slaveUnitName);
		for (SensorUnitMaster sensorUnitMaster : sensorUnits) {
			sensorUnitMaster.setSlaveUnitID(slaveUnit.getSlaveUnitID());
			sensorDAOImpl.addSensorUnit(sensorUnitMaster);
		}
		return Response.status(200).entity(new Success("All Sensors Added Successfully", "200")).build();
	}
}
