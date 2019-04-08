package org.plugin.cloud.db.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("masterDAOImpl")
public class MasterDAOImpl implements MasterDAO, MasterQueries {
	public JdbcTemplate jdbcTemplate;
	@Autowired public MasterDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
