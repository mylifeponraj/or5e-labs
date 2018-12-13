package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.FDDetailsMaster;
import org.plugin.cloud.db.mapper.ExpenceMapping;
import org.plugin.cloud.db.mapper.FDDetailsMapper;
import org.plugin.cloud.request.Expences;
import org.plugin.cloud.request.FDDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("expenceDAOImpl")
public class ExpenceDAOImpl implements ExpenceDAO {
	public JdbcTemplate jdbcTemplate;
	private final String GET_ALL_EXPENCES = "select * from expence_history where userid=?";
	private final String INSERT_EXPENCES = "insert into expence_history (exp_id, exp_amt, userid, exp_date) values (?, ?, ?, current_timestamp)";
	private final String INSERT_FD = "insert into finfdmaster (fd_number, fd_name, fd_date, fd_amount, fd_maturity_date, fd_maturity_amt, fd_userid, fd_status) values (?, ?, TO_DATE(?, 'MM/DD/YYYY'), ?, TO_DATE(?, 'MM/DD/YYYY'), ?, ?, 'Y')";
	private final String GET_ALL_FD = "select * from finfdmaster where userid=?";

	public ExpenceDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override public List<Expences> getAllExpences(String userID) {
		return jdbcTemplate.query(GET_ALL_EXPENCES, new ExpenceMapping(), Integer.parseInt(userID));
	}
	@Override public List<FDDetailsMaster> getAllFD(String userID) {
		return jdbcTemplate.query(GET_ALL_FD, new FDDetailsMapper(), Integer.parseInt(userID));
	}
	@Override public Boolean createFDRecord(FDDetails fdDetails) {
		int updateStatus = jdbcTemplate.update(INSERT_FD, Integer.parseInt(fdDetails.getFdNumber()), fdDetails.getFdName(), fdDetails.getFdDepDate(), Integer.parseInt(fdDetails.getFdDepAmt()), fdDetails.getFdMatDate(), Integer.parseInt(fdDetails.getFdMatAmt()), Integer.parseInt(fdDetails.getUserID()));
		return (updateStatus>1);
	}
	@Override
	public Boolean createExpenceRecord(Expences expence) {
		int updateStatus = jdbcTemplate.update(INSERT_EXPENCES, expence.getExpenceType(), expence.getExpenceAmount(), Integer.parseInt(expence.getUserID()));
		return (updateStatus>1);
	}

}
