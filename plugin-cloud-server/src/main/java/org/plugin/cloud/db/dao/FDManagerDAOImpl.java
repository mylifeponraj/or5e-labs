package org.plugin.cloud.db.dao;

import java.util.List;

import org.plugin.cloud.db.FDDetailsMaster;
import org.plugin.cloud.db.mapper.FDDetailsMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("fdManagerDAOImpl")
public class FDManagerDAOImpl implements FDManagerDAO {
	public JdbcTemplate jdbcTemplate;
	private final String GET_ALL_FD = "select * from finfdmaster";
	private final String GET_FD_FOR_USER = "select * from finfdmaster where fd_userid=?";
	private final String GET_FD_FOR_USER_AFTER_TODAY = "select * from finfdmaster where fd_userid=? and fd_maturity_date>?";

	@Override public List<FDDetailsMaster> getAllFDDetailsForUser(String userID, Boolean postToday) {
		return jdbcTemplate.query(((postToday)?GET_FD_FOR_USER:GET_FD_FOR_USER_AFTER_TODAY), new FDDetailsMapper());
	}

	@Override public Boolean getAllFDDetailsForUser() {
		return (jdbcTemplate.query(GET_ALL_FD, new FDDetailsMapper())!=null);
	}
}