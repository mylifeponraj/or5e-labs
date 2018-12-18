package org.plugin.cloud.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.plugin.cloud.db.FDDetailsMaster;
import org.plugin.cloud.db.mapper.ExpenceMapping;
import org.plugin.cloud.db.mapper.FDDetailsMapper;
import org.plugin.cloud.request.Dashboard;
import org.plugin.cloud.request.Expences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("dashbaordDAOImpl")
public class DashboardManagerDAOImpl implements DashboardManagerDAO, ExpencesQueries, FDQueries{
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public DashboardManagerDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Dashboard getFinanceDashboard(String userid) {
		Dashboard dashboard = new Dashboard();
		List<FDDetailsMaster> fdList = jdbcTemplate.query(GET_FD_FOR_USER, new FDDetailsMapper(), Integer.parseInt(userid));
		List<Expences> userExpences = jdbcTemplate.query(GET_ALL_EXP_FOR_USER, new ExpenceMapping(), Integer.parseInt(userid));
		dashboard.setExpenceList(userExpences);
		dashboard.setFdDetails(fdList);
		int totalExpences = 0;
		for (Expences expences : userExpences) {
			totalExpences += Integer.parseInt(expences.getExpenceAmount());
		}
		dashboard.setTotalExpence(Double.parseDouble(totalExpences+""));
		
		int totalDeposit = 0;
		int totalMaturityAmt=0;
		int totalDepositAmt=0;
		for (FDDetailsMaster fdDetail : fdList) {
			totalDeposit++;
			totalMaturityAmt += fdDetail.getFdMaturityAmount();
			totalDepositAmt += fdDetail.getFdAmount();
		}
		dashboard.setTotalFDCount(totalDeposit);
		dashboard.setTotalFDDeposit(totalDepositAmt);
		dashboard.setTotalFDMaturity(totalMaturityAmt);

		return dashboard;
	}
}
