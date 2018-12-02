package org.plugin.cloud.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.plugin.cloud.db.ExpenceType;
import org.springframework.jdbc.core.RowMapper;

public class ExpenceTypeMapper implements RowMapper<ExpenceType>{

	@Override public ExpenceType mapRow(ResultSet reseltSet, int rowNum) throws SQLException {
		ExpenceType expenceType = new ExpenceType();
		expenceType.setExpenceID(reseltSet.getInt("exp_id"));
		expenceType.setExpenceName(reseltSet.getString("exp_name"));
		expenceType.setExpenceStatus(reseltSet.getString("exp_status"));
		return expenceType;
	}

}
