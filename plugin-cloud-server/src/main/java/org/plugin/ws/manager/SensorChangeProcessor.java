package org.plugin.ws.manager;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.plugin.cloud.db.dao.SlaveUnitDAOImpl;
import org.plugin.ws.tx.Message;

public class SensorChangeProcessor implements RequestProcessor {
	//{"messagefrom":"MCU_SUTHAP_01", "messageto":"HAServer", "messagetype":"SVC", "message":"SU_01_SUTHAP|2|<ON>-<OFF>-<T>"}
	@Override public void process(Session session, Message message) {
		SlaveUnitDAOImpl suDAO = (SlaveUnitDAOImpl)RequestManagerContextHelper.getRequestManagerContext().getBean("slvDAOImpl");
		StringTokenizer tokens = new StringTokenizer(message.getMessage(), "|");

		Integer slaveUnitID = Integer.parseInt(tokens.nextToken());
		Integer switchID = Integer.parseInt(tokens.nextToken());
		String status = tokens.nextToken();
		Boolean switchStatus = Boolean.FALSE;
		Boolean toggle = Boolean.FALSE;
		switch(status) {
		case "ON":
			switchStatus = Boolean.TRUE;
			break;
		case "OFF":
			switchStatus = Boolean.FALSE;
			break;
		case "T":
			toggle = Boolean.TRUE;
			break;			
		}

		Message response = new Message();
		response.setMessageFrom(message.getMessageTo());
		response.setMessageTo(message.getMessageFrom());
		response.setMessageType(message.getMessageType()+"RS");
		if(suDAO.updateSwitchStatus(slaveUnitID, switchID, switchStatus, toggle)) {
			response.setMessage("Success");
		}
		else {
			response.setMessage("Failed");
		}
		
		try {
			session.getBasicRemote().sendObject(response);
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}

}
