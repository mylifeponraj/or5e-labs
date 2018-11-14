package org.or5e.core.ds;

import java.sql.Connection;

public interface ConnectionSource {
	public void connect();
	public Connection getConnectionFromThePool();
	public void releaseConnection(Connection con);
	public void disconnect();
}