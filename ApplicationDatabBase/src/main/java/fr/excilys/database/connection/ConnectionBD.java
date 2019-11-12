package fr.excilys.database.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionBD {
	private static Connection connect;
	@Autowired
	private DataSource dataSource;

	public Connection getConnection() {
		try {
			connect = dataSource.getConnection();
		} catch (SQLException se) {
			for (Throwable e : se) {
				System.err.println("Erreurs : " + e);
			}
		}
		return connect;
	}

	public static void closeConnection() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				for (Throwable es : e) {
					System.err.println("Erreurs : " + es);
				}
			}
		}
	}

}
