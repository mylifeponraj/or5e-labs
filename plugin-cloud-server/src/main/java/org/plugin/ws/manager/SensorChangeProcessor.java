package org.plugin.ws.manager;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.plugin.cloud.db.dao.SlaveUnitDAOImpl;
import org.plugin.ws.tx.Message;

public class SensorChangeProcessor implements RequestProcessor {
	//{"messagefrom":"MCU_SUTHAP_01", "messageto":"HAServer", "messagetype":"SVC", "message":"SU_01_SUTHAP|2|true"}
	@Override public void process(Session session, Message message) {
		SlaveUnitDAOImpl suDAO = (SlaveUnitDAOImpl)RequestManagerContextHelper.getRequestManagerContext().getBean("slvDAOImpl");
		StringTokenizer tokens = new StringTokenizer(message.getMessage(), "|");
		String slaveUnitName = tokens.nextToken();
		Integer switchID = Integer.parseInt(tokens.nextToken());
		Boolean switchStatus = tokens.nextToken().equalsIgnoreCase("TRUE");
		Message response = new Message();
		response.setMessageFrom(message.getMessageTo());
		response.setMessageTo(message.getMessageFrom());
		response.setMessageType(message.getMessageType());
		if(suDAO.updateSwitchStatus(slaveUnitName, switchID, switchStatus)) {
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
