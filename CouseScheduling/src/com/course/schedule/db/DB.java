package com.course.schedule.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.couse.schedule.service.Constants;

public class DB {

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(Constants.url,
					Constants.USER, Constants.PASS);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}
}
