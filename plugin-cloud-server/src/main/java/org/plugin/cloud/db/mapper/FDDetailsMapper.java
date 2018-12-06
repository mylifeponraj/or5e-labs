package org.plugin.cloud.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.plugin.cloud.db.FDDetailsMaster;
import org.springframework.jdbc.core.RowMapper;

public class FDDetailsMapper implements RowMapper<FDDetailsMaster>{

	@Override public FDDetailsMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
		FDDetailsMaster fdMaster = new FDDetailsMaster();
		fdMaster.setFdAmount(rs.getInt("fd_amount"));
		fdMaster.setFdDepositDate(rs.getTimestamp("fd_date"));
		fdMaster.setFdID(rs.getInt("fd_id"));
		fdMaster.setFdMaturityAmount(rs.getInt("fd_maturity_amt"));
		fdMaster.setFdMaturityDate(rs.getTimestamp("fd_maturity_date"));
		fdMaster.setFdName(rs.getString("fd_name"));
		fdMaster.setFdNumber(rs.getString("fd_number"));
		fdMaster.setFdUserID(rs.getInt("fd_userid"));
		return fdMaster;
	}
}