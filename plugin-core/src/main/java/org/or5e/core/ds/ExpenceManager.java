package org.or5e.core.ds;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.or5e.core.ds.vo.ExpenceType;

public class ExpenceManager {
	private final ConnectionSource conSource = DBConnectionSource.getDataSource();
	public List<ExpenceType> getExpenceType() throws SQLException {
		Connection dbCon = conSource.getConnectionFromThePool();
		Statement expenceTypeStatement = dbCon.createStatement();
		ResultSet expTypeRS = expenceTypeStatement.executeQuery(DBQueries.GET_EXPENCE_TYPE);
		List<ExpenceType> expType = new LinkedList<>();
		while(expTypeRS.next()) {
			ExpenceType eType = new ExpenceType();
			eType.setId(expTypeRS.getInt(1));
			eType.setName(expTypeRS.getString(2));
			eType.setStatus(expTypeRS.getString(3));
			expType.add(eType);
		}
		conSource.releaseConnection(dbCon);
		return expType;
	}
	
	public static void main(String[] args) throws SQLException{
		ExpenceManager em = new ExpenceManager();
		System.out.println(em.getExpenceType());
	}
}
