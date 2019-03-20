package org.plugin.ws.manager;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.plugin.cloud.db.dao.MCUDetailsDAO;
import org.plugin.ws.session.SessionPoolManagerImpl;
import org.plugin.ws.tx.Message;
import org.plugin.ws.tx.MessageType;

public class RegisterMCUProcessor implements RequestProcessor{
	@Override public void process(Session session, Message message) {
		Message response = new Message();
		Boolean addSession = SessionPoolManagerImpl.getSessionPoolManager().addSession(session, message);
		response.setMessageFrom("HAServer");
		if(addSession) {
			response.setMessageTo(message.getMessageFrom());
			try {
				String requestMessage = message.getMessage();
				
				MCUDetailsDAO mcuDAO = (MCUDetailsDAO)RequestManagerContextHelper.getRequestManagerContext().getBean("mcuDetailsDAOImpl");
				if(validateRequestMessage(requestMessage, mcuDAO)) {
					mcuDAO.activateMCU(message.getMessageFrom());
					response.setMessageType(MessageType.REG.name());
					response.setMessage("Welcome!!!");
				}
				else {
					response.setMessageType(MessageType.ERRRES.name());
					response.setMessage("User Not able to bind or add to messaging service.");
				}
				session.getBasicRemote().sendObject(response);
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}
		}
		else {
			response.setMessageTo(message.getMessageFrom());
			response.setMessageType(MessageType.ERRRES.name());
			response.setMessage("Not able to add you.");
			try {
				session.getBasicRemote().sendObject(response);
				session.close();
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}
		}
	}

	private Boolean validateRequestMessage(String requestMessage, MCUDetailsDAO mcuDAO) {
		StringTokenizer tokens = new StringTokenizer(requestMessage, ",");
		String userName = tokens.nextToken();
		String license = tokens.nextToken();
		String ipAddress = tokens.nextToken();
		return mcuDAO.validateUser(userName, license, ipAddress);
	}
}
