package org.plugin.cloud.db.request.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.plugin.cloud.db.DBHelper;
import org.plugin.cloud.request.Expences;
import org.springframework.jdbc.core.RowMapper;

public class ExpenceMapping implements RowMapper<Expences> {

	@Override public Expences mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Expences expences = new Expences();
		expences.setExpenceID(resultSet.getInt("exp_his_id"));
		expences.setExpenceType(resultSet.getInt("exp_id"));
		expences.setExpenceDate(DBHelper.convertSQLTimestampToDate(resultSet.getTimestamp("exp_date")));
		expences.setExpenceAmount(resultSet.getString("exp_amt"));
		return expences;
	}

}
