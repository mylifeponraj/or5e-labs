package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.FDDetailsMaster;
import org.plugin.cloud.request.Expences;
import org.plugin.cloud.request.FDDetails;

public interface ExpenceDAO {
	public List<Expences> getAllExpences(String userID);
	public List<FDDetailsMaster> getAllFD(String userID);
	public Boolean createExpenceRecord(Expences expence);
	public Boolean createFDRecord(FDDetails fdDetails);
}