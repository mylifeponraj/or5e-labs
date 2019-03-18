package org.plugin.ws.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.websocket.EncodeException;
import javax.websocket.Session;

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
		sessionCache.entrySet().removeIf(matches -> matches.getValue().getId().equals(session.getId()));
		
		//Removing if it is a admin Session
		adminSessions.removeIf(matches -> matches.getId().equals(session.getId()));
	}
	
	@Override public void sendAdminForMCUChange(Message message) {
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
