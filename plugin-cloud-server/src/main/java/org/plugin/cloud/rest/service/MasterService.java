package org.plugin.cloud.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.db.MCUDetails;
import org.plugin.cloud.db.SensorUnitMaster;
import org.plugin.cloud.db.SlaveUnitMaster;
import org.plugin.cloud.db.UserMaster;
import org.plugin.cloud.db.dao.MCUDetailsDAOImpl;
import org.plugin.cloud.db.dao.SensorDAOImpl;
import org.plugin.cloud.db.dao.SlaveUnitDAOImpl;
import org.plugin.cloud.db.dao.UserMasterDAOImpl;
import org.plugin.cloud.request.HomeAutomationDetails;
import org.plugin.cloud.request.SlaveUnitDetails;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/master")
public class MasterService {

	@Autowired SensorDAOImpl sensorDAOImpl;
	@Autowired SlaveUnitDAOImpl slaveDAOImpl;
	@Autowired MCUDetailsDAOImpl mcuDAOImpl;
	@Autowired UserMasterDAOImpl userDAOImpl;
	
	@GET
	@Path("/getConfiguration/{userName}")
	@Produces (MediaType.APPLICATION_JSON)
	public Response getConfiguraitonForUserName(@PathParam("userName") String userName) {
		List<SlaveUnitDetails> slaveUnitList = new ArrayList<>();

		UserMaster userMaster = userDAOImpl.getUserByName(userName);
		MCUDetails mcuDetails = mcuDAOImpl.getMCUDetailsForUserByID(userMaster.getUserID());
		List<SlaveUnitMaster> slaveUnits = slaveDAOImpl.getSlaveUnitByMasterUnitID(mcuDetails.getMasterUnitId());
		for (SlaveUnitMaster slaveUnit : slaveUnits) {
			SlaveUnitDetails slaveUnitDetail = new SlaveUnitDetails();
			slaveUnitDetail.mapSlaveUnitMaster(slaveUnit);
			List<SensorUnitMaster> sensorsList = sensorDAOImpl.getAllSensorUnitMappedToSlaveUnit(slaveUnit.getSlaveUnitID());
			for (SensorUnitMaster sensors : sensorsList) {
				slaveUnitDetail.addSensors(sensors);
			}
			slaveUnitList.add(slaveUnitDetail);
		}

		HomeAutomationDetails response = new HomeAutomationDetails();
		response.setMcuDetails(mcuDetails);
		response.setUserMaster(userMaster);
		response.setSlaveUnits(slaveUnitList);

		return Response.status(200).entity(response).build();
	}
}
