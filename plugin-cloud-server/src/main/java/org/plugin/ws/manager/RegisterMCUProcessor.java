package org.plugin.ws.manager;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.plugin.cloud.db.SlaveUnitMaster;
import org.plugin.cloud.db.dao.MCUDetailsDAO;
import org.plugin.cloud.db.dao.SlaveUnitDAOImpl;
import org.plugin.ws.session.SessionPoolManagerImpl;
import org.plugin.ws.tx.Message;
import org.plugin.ws.tx.MessageType;

public class RegisterMCUProcessor implements RequestProcessor{
	@Override public void process(Session session, Message message) {
		Message response = new Message();
		Boolean addSession = SessionPoolManagerImpl.getSessionPoolManager().addSession(session, message);
		response.setMessageFrom("HAServer");
		if(addSession) {
			try {
				String requestMessage = message.getMessage();
				if(message.getMessageFrom().equals("HAServerAPP")) {
					response.setMessageTo("HAServerAPP");
					response.setMessageType(MessageType.REG.name());
					response.setMessage("Welcome.");
					try {
						session.getBasicRemote().sendObject(response);
					} catch (IOException | EncodeException e) {
						e.printStackTrace();
					}
					return;
				}
				MCUDetailsDAO mcuDAO = (MCUDetailsDAO)RequestManagerContextHelper.getRequestManagerContext().getBean("mcuDetailsDAOImpl");

				Message adminMessage = new Message();
				adminMessage.setMessageFrom("HAServer");
				adminMessage.setMessageTo("HAServerAPP");
				adminMessage.setMessage(message.getMessageFrom());
				response.setMessageTo(message.getMessageFrom());

				if(validateRequestMessage(requestMessage, mcuDAO)) {
					mcuDAO.activateMCU(message.getMessageFrom());
					response.setMessageType(MessageType.REG.name());
					adminMessage.setMessageType("ACTMCU");
					response.setMessage("CNF");
				}
				else {
					mcuDAO.deactivateMCU(message.getMessageFrom());
					response.setMessageType(MessageType.ERRRES.name());
					response.setMessage("User Not able to bind or add to messaging service.");
					adminMessage.setMessageType("DISMCU");
				}
				session.getBasicRemote().sendObject(response);
				SessionPoolManagerImpl.getSessionPoolManager().sendAdminForMCUChange(adminMessage);
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}
		}
		else {
			response.setMessageTo(message.getMessageFrom());
			response.setMessageType(MessageType.ERRRES.name());
			response.setMessage("You have already registered!!!");
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
		if(userName.equals("ADMIN")) {
			return Boolean.TRUE;
		}
		String masterUnitName = tokens.nextToken();
		String ipAddress = tokens.nextToken();
		String license = tokens.nextToken();
		String version = tokens.nextToken();
		return mcuDAO.validateUser(userName, license, ipAddress);
	}
}
