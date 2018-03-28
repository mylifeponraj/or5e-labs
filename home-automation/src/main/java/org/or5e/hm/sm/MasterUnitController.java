package org.or5e.hm.sm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.websocket.Session;

public class MasterUnitController {
	private static MasterUnitController _controller;
	private final Set<String> allUsers = new HashSet<>();
	private final Map<String, Object> allUserSessions = new ConcurrentHashMap<>();
	private final Set<String> currentOnlineUsers = new ConcurrentSkipListSet<>();

	static {
		_controller = new MasterUnitController();
	}
	public static MasterUnitController getController() {
		return _controller;
	}
	public void addAllUsers(List<String> userList) {
		for (String user : userList) {
			allUsers.add(user);
			allUserSessions.put(user, "NULL");
		}
	}
	public void makeUserAvailable(String user, Session session) {
		makeUserAvailable(user, session);
	}
	public void makeUserNotAvailable(String user) {
		removeUser(user);
		
	}
	public void addUser(String user, Session session) {
		currentOnlineUsers.add(user);
		allUserSessions.put(user, session);
	}

	public List<String> getAllOnlineUsers() {
		List<String> userList = new LinkedList<>();
		for (String user : currentOnlineUsers) {
			userList.add(user);
		}
		return userList;
	}
	public void removeUser(String user) {
		allUserSessions.put(user, "NULL");
		currentOnlineUsers.remove(user);
	}
	public List<String> getAllOfflineCustomers() {
		List<String> offlineCustomers = new LinkedList<>();
		Set<String> allUserMap = allUserSessions.keySet();
		for (String user : allUserMap) {
			if(allUserSessions.get(user) == "NULL") {
				offlineCustomers.add(user);
			}
		}
		return offlineCustomers;
	}
	public Map<String, Object> getAllUserSessions() {
		return allUserSessions;
	}
	
	public static void main(String[] args) {
		MasterUnitController controller = MasterUnitController.getController();
		controller.removeUser("ponraj");
	}
}
