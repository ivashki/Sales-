package com.frantishex.locking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lock {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db_example?serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "password";

	public boolean lock(String lockString) throws SQLException {

		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
			try (PreparedStatement ps = con
					.prepareStatement("select * from db_example.lockedresources where name = ?")) {
				ps.setString(1, lockString);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {

					System.out.println("In use");
					return false;
				} else {
					try (PreparedStatement ps2 = con
							.prepareStatement("insert into db_example.lockedresources (name) values (?);")) {
						ps2.setString(1, lockString);
						ps2.executeUpdate();
					}
					return true;
				}
			}
		}
	}

	public void unlock(String unlockString) throws SQLException {

		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
			try (PreparedStatement ps = con
					.prepareStatement("delete * from db_example.lockedresources where name = ?")) {
				ps.setString(1, unlockString);
				ps.executeUpdate();

			}
		}
	}
}