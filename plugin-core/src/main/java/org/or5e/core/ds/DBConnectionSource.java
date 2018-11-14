package org.or5e.core.ds;

import java.sql.Connection;
import java.sql.SQLException;

import org.or5e.core.BaseObject;
import org.postgresql.ds.PGPoolingDataSource;

public class DBConnectionSource extends BaseObject implements ConnectionSource {
	private PGPoolingDataSource source = new PGPoolingDataSource();
	private volatile static Boolean isInitilized = Boolean.FALSE;
	private static DBConnectionSource _dataSource;
	private DBConnectionSource() {
	}
	public static DBConnectionSource getDataSource() {
		if(_dataSource == null) {
			_dataSource = new DBConnectionSource();
			_dataSource.connect();
		}
		return _dataSource;
	}
	@Override public void connect() {
		source.setDataSourceName(getProperties("dataSource"));
		source.setServerName(getProperties("dbServer"));
		source.setDatabaseName(getProperties("database"));
		source.setUser(getProperties("dbUsername"));
		source.setPassword(getProperties("dbPassword"));
		source.setMaxConnections(Integer.parseInt(getProperties("maxConnection")));
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override public void run() {
				disconnect();
			}
		});
		isInitilized = Boolean.TRUE;
	}
	@Override public Connection getConnectionFromThePool() {
		try {
			return source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override public void releaseConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override public void disconnect() {
		if(isInitilized) {
			isInitilized = Boolean.FALSE;
			debug("Shutting Down the Database connection pool");
			source.close();
		}
	}

	@Override
	public String getName() {
		return "org.or5e.core.ds.DBConnectionSource";
	}
	public static void main(String[] args) {
		ConnectionSource src = new DBConnectionSource();
		src.connect();
		System.out.println(src.getConnectionFromThePool());
		src.disconnect();
	}
}