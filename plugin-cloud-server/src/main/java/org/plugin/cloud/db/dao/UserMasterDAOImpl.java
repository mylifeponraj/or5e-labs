package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.UserMaster;
import org.plugin.cloud.db.mapper.UserMasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("userMasterDAOImpl")
public class UserMasterDAOImpl implements UserMasterDAO, UserMasterQueries {
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public UserMasterDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override public List<UserMaster> getAllUsers() {
		return jdbcTemplate.query(GET_ALL_USERS, new UserMasterMapper());
	}

	@Override public UserMaster isValidUser(String username, String userKey) {
		try {
			return jdbcTemplate.queryForObject(IS_VALID_USER, new UserMasterMapper(), username, (String)(userKey.hashCode()+""));
		}
		catch(EmptyResultDataAccessException emptyException) {
			return null;
		}
	}

	@Override
	public Boolean createUser(UserMaster user) {
		return jdbcTemplate.update(ADD_USER, user.getUserName(), user.getUserKey().hashCode(), 'Y', user.getUserType(), user.getDisplayName()) > 0;
	}

	@Override
	public Boolean deactivateUser(String userID) {
		return jdbcTemplate.update(DEACTIVATE_VALID_USER, new Object[] {userID}) > 1;
	}

	@Override
	public Boolean activateUser(String userID) {
		return jdbcTemplate.update(ACTIVATE_VALID_USER, new Object[] {userID}) > 1;
	}
	public static void main(String[] args) {
		System.out.println("Welcome123#".hashCode()+":1663027827");
	}
}
