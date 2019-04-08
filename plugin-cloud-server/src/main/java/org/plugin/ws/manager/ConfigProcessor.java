package org.plugin.ws.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.Session;

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
import org.plugin.ws.tx.Message;

import com.google.gson.Gson;

public class ConfigProcessor implements RequestProcessor {

	@Override public void process(Session session, Message message) {
		String userName = message.getMessage();
		List<SlaveUnitDetails> slaveUnitList = new ArrayList<>();

		SensorDAOImpl sensorDAOImpl = (SensorDAOImpl)RequestManagerContextHelper.getRequestManagerContext().getBean("sensorDAOImpl");
		SlaveUnitDAOImpl slaveDAOImpl = (SlaveUnitDAOImpl)RequestManagerContextHelper.getRequestManagerContext().getBean("slvDAOImpl");
		MCUDetailsDAOImpl mcuDAOImpl = (MCUDetailsDAOImpl)RequestManagerContextHelper.getRequestManagerContext().getBean("mcuDetailsDAOImpl");
		UserMasterDAOImpl userDAOImpl = (UserMasterDAOImpl)RequestManagerContextHelper.getRequestManagerContext().getBean("userMasterDAOImpl");
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
		
		Gson gson = new Gson();
		Message responseMsg = new Message();
		responseMsg.setMessageFrom("HAServer");
		responseMsg.setMessageTo(message.getMessageFrom());
		responseMsg.setMessageType("CNF");
		responseMsg.setMessage(gson.toJson(response));
		
		try {
			session.getBasicRemote().sendObject(responseMsg);
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}

}
