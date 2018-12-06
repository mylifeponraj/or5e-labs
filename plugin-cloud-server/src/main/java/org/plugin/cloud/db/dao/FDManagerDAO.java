package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.FDDetailsMaster;

public interface FDManagerDAO {
	public List<FDDetailsMaster> getAllFDDetailsForUser(String userID, Boolean postToday);
	public Boolean getAllFDDetailsForUser();
}