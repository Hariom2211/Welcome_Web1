package com.pw.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	private static Properties dbProps;

	static {
		dbProps = new Properties();
		try {
			dbProps.load(ConnectionFactory.class.getClassLoader()
					.getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws Exception {
		Connection con = null;

		if (dbProps == null) {
			throw new Exception(
					"Database configuration is in-correct, please check");
		}
		Class.forName(dbProps.getProperty("db.driverClass"));
		con = DriverManager.getConnection(dbProps.getProperty("db.url"),
				dbProps.getProperty("db.username"),
				dbProps.getProperty("db.password"));
		con.setAutoCommit(false);
		return con;
	}

}
