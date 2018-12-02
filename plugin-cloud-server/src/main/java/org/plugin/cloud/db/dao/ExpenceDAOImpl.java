package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.mapper.ExpenceMapping;
import org.plugin.cloud.request.Expences;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("expenceDAOImpl")
public class ExpenceDAOImpl implements ExpenceDAO {
	public JdbcTemplate jdbcTemplate;
	private final String GET_ALL_EXPENCES = "select * from expence_history where userid=?";
	private final String INSERT_EXPENCES = "insert into expence_history (exp_id, exp_amt, userid, exp_date) values (?, ?, ?, current_timestamp)";
	
	public ExpenceDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override public List<Expences> getAllExpences(String userID) {
		return jdbcTemplate.query(GET_ALL_EXPENCES, new ExpenceMapping(), Integer.parseInt(userID));
	}

	@Override
	public Boolean createExpenceRecord(Expences expence) {
		int updateStatus = jdbcTemplate.update(INSERT_EXPENCES, expence.getExpenceType(), expence.getExpenceAmount(), Integer.parseInt(expence.getUserID()));
		return (updateStatus>1);
	}

}
