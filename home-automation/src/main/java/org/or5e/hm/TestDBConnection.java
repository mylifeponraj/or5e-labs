package org.or5e.hm;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection {
	public static void main(String args[]) {
		String passcode = "mYnAMEiSpONRAJ123!";
		System.out.println(passcode.hashCode());
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HOME_AUTOMATION", "postgres", "Welcome123#");
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
}
