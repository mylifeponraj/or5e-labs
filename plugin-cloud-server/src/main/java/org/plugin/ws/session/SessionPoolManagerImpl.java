package org.plugin.ws.session;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.plugin.cloud.db.dao.MCUDetailsDAO;
import org.plugin.ws.manager.RequestManagerContextHelper;
import org.plugin.ws.tx.Message;

public class SessionPoolManagerImpl implements SessionPoolManager {
	
	private Map<String, Session> sessionCache = new HashMap<>();
	private List<Session> adminSessions = new LinkedList<>();
	private static SessionPoolManager _manager;
	private SessionPoolManagerImpl() { }
	public static SessionPoolManager getSessionPoolManager() {
		if(_manager == null) {
			_manager = new SessionPoolManagerImpl();
		}
		
		return _manager;
	}
	
	@Override public Boolean addSession(Session session, Message message) {
		if(message.getMessageFrom().equals("HAServerAPP")) {
			adminSessions.add(session);
			return Boolean.TRUE;
		}
		else if(sessionCache.get(message.getMessageFrom()) != null) {
			return Boolean.FALSE;
		}
		sessionCache.put(message.getMessageFrom(), session);
		return Boolean.TRUE;
	}

	@Override public void deleteSession(Session session) {
		//Removing if it is a customer session
//		sessionCache.entrySet().removeIf(matches -> matches.getValue().getId().equals(session.getId()));
		Set<String> sessionKeys = sessionCache.keySet();
		String deletedKey="";
		for (String keys : sessionKeys) {
			if(sessionCache.get(keys).getId().equals(session.getId())) {
				Message adminMessage = new Message();
				adminMessage.setMessageFrom("HAServer");
				adminMessage.setMessageTo("HAServerApp");
				adminMessage.setMessageType("DISMCU");
				adminMessage.setMessage(keys);
				SessionPoolManagerImpl.getSessionPoolManager().sendAdminForMCUChange(adminMessage);
				deletedKey = keys;
				MCUDetailsDAO mcuDAO = (MCUDetailsDAO)RequestManagerContextHelper.getRequestManagerContext().getBean("mcuDetailsDAOImpl");
				mcuDAO.deactivateMCU(deletedKey);
				break;
			}
		}
		sessionCache.remove(deletedKey);
		
		//Removing if it is a admin Session
		if(deletedKey.equals(""))
			adminSessions.removeIf(matches -> matches.getId().equals(session.getId()));
	}
	
	@Override public void sendAdminForMCUChange(Message message) {
		System.out.println("About to send Message to All Admin Sessions : "+adminSessions.size());
		if(adminSessions.size() > 0) {
			for (Session session : adminSessions) {
				try {
					session.getBasicRemote().sendObject(message);
				} catch (IOException | EncodeException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
