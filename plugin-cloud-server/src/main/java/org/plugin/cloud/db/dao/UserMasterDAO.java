package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.UserMaster;

public interface UserMasterDAO {
	public List<UserMaster> getAllUsers();
	public List<UserMaster> getUserLike(String username);
	public List<UserMaster> getUser(String userID);
	public UserMaster isValidUser(String username, String userKey);
	public Boolean createUser(UserMaster user);
	public Boolean deactivateUser(String userID);
	public Boolean activateUser(String userID);
	public Boolean updateUserLicense(String userID, String userLicense);
}
