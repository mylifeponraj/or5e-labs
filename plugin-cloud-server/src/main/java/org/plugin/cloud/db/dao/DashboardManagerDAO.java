package org.plugin.cloud.db.dao;

import org.plugin.cloud.request.Dashboard;

public interface DashboardManagerDAO {
	public Dashboard getFinanceDashboard(String userid);
}
