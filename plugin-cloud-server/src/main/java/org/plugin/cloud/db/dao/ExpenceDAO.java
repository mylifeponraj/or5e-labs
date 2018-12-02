package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.request.Expences;

public interface ExpenceDAO {
	public List<Expences> getAllExpences(String userID);
	public Boolean createExpenceRecord(Expences expence);
}