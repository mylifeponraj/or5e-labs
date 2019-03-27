package org.plugin.ws.manager;

import java.io.IOException;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.plugin.cloud.db.MCUDetails;
import org.plugin.cloud.db.dao.MCUDetailsDAO;
import org.plugin.ws.tx.Message;
import org.plugin.ws.tx.MessageType;

public class MasterUnitUpdateProcessor implements RequestProcessor {

	@Override public void process(Session session, Message message) {
		MCUDetailsDAO mcuDAO = (MCUDetailsDAO)RequestManagerContextHelper.getRequestManagerContext().getBean("mcuDetailsDAOImpl");
		List<MCUDetails> allActiveMCUDetails = mcuDAO.getAllActiveMCUDetails();
		StringBuilder builder = new StringBuilder();
		for (MCUDetails mcuDetails : allActiveMCUDetails) {
			builder.append(mcuDetails.getMasterUnitName()+",");
		}
		Message response = new Message();
		response.setMessageFrom("HAServer");
		response.setMessageTo("HAServerAPP");
		response.setMessageType(MessageType.MUU.name());
		response.setMessage(builder.toString());
		
		try {
			session.getBasicRemote().sendObject(response);
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}

}
