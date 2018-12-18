package org.plugin.cloud.db.dao;

public interface ExpencesQueries {
	public final String GET_ALL_EXP_TYPE = "select * from expence_type";
	public final String GET_ALL_EXP_FOR_USER = "select * from expence_history where userid=?";
	public final String GET_ALL_EXP = "select * from expence_history";
	public final String DEACTIVATE_EXP_TYPE = "update expence_type set exp_status='N' where exp_id=?";
	public final String ACTIVATE_EXP_TYPE = "update expence_type set exp_status='Y' where exp_id=?";
}
