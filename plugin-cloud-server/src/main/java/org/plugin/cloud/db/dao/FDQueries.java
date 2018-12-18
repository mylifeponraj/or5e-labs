package org.plugin.cloud.db.dao;

public interface FDQueries {
	public final String GET_ALL_FD = "select * from finfdmaster";
	public final String GET_FD_FOR_USER = "select * from finfdmaster where fd_userid=?";
	public final String GET_FD_FOR_USER_AFTER_TODAY = "select * from finfdmaster where fd_userid=? and fd_maturity_date>now()";
}
