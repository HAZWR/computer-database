package fr.excilys.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String urlH2="jdbc:h2:mem:computer-database-db;INIT=RUNSCRIPT FROM '~/h2Requests.sql'";
	private static String user = "admincdb";
	private static String passwd = "qwerty1234";
	private static Connection connect;


	private ConnectionBD() throws SQLException {
		try {
		  if(System.getProperty("testing").contentEquals("false"))
			connect = DriverManager.getConnection(url, user, passwd);
		  else
			connect = DriverManager.getConnection(urlH2,user,passwd);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() throws SQLException {
		if (connect == null || connect.isClosed()) {
			new ConnectionBD();
			System.out.println("INSTANCIATION DE LA CONNEXION SQL ! ");
		} else {
			System.out.println("CONNEXION SQL EXISTANTE ! ");
		}
		return connect;
	}

}
