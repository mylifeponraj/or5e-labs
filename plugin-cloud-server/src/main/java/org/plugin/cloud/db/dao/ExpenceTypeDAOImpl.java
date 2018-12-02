package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.ExpenceType;
import org.plugin.cloud.db.mapper.ExpenceTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("expenceTypeDAOImpl")
public class ExpenceTypeDAOImpl implements ExpenceTypeDAO{
	
	public JdbcTemplate jdbcTemplate;
	
	private final String GET_ALL_EXP_TYPE = "select * from expence_type";
	private final String DEACTIVATE_EXP_TYPE = "update expence_type set exp_status='N' where exp_id=?";
	private final String ACTIVATE_EXP_TYPE = "update expence_type set exp_status='Y' where exp_id=?";

	@Autowired
	public ExpenceTypeDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ExpenceType> getAllExpenceType() {
		return jdbcTemplate.query(GET_ALL_EXP_TYPE, new ExpenceTypeMapper());
	}

	@Override
	public Boolean createExpenceType(ExpenceType user) {
		return null;
	}

	@Override
	public Boolean deactivateUser(String userID) {
		return (jdbcTemplate.update(DEACTIVATE_EXP_TYPE, userID)>0);
	}

	@Override
	public Boolean activateUser(String userID) {
		return (jdbcTemplate.update(ACTIVATE_EXP_TYPE, userID)>0);
	}
}
