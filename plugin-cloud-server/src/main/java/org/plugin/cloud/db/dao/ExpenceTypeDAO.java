package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.ExpenceType;

public interface ExpenceTypeDAO {
	public List<ExpenceType> getAllExpenceType();
	public Boolean createExpenceType(ExpenceType user);
	public Boolean deactivateUser(String userID);
	public Boolean activateUser(String userID);
}
