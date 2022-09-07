package com.fashion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {
	private static Connection con;

	private ConnectionUtil() {
		con = null;
	}

	// This method will be designed to give me a connection object
	public static Connection getConnection() {

		// Determine if we already have an existing connection, if so just return that
		try {

			if (con != null && !con.isClosed()) {
				return con;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		String url, user, pass;

		Properties prop = new Properties();

		try {
			Class.forName("org.postgresql.Driver");

			// I've loaded the property file from my resource folder for my prop object to
			// use
//			prop.load(new FileReader(
//					"C:\\Users\\Stephen - Work\\Documents\\Revature\\Java\\trainer-code\\Week-4\\rest\\src\\main\\resources\\database.properties"));

//			url = prop.getProperty("url");
//			user = prop.getProperty("user");
//			pass = prop.getProperty("pass");
			url = "jdbc:postgresql://p1.cusrlszc5n50.us-east-1.rds.amazonaws.com:5432/postgres";
			user = "postgres";
			pass = "12345678";

			con = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return con;
	}
}
