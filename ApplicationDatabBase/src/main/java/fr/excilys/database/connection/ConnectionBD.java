package fr.excilys.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionBD {
	private static String urlH2="jdbc:h2:mem:computer-database-db;INIT=RUNSCRIPT FROM '/home/excilys/Documents/h2Requests.sql'";
	private static Connection connect;
	private String configFile= "/db.properties";
	private HikariConfig cfg;
	private HikariDataSource ds;

	private ConnectionBD() throws SQLException {
		try {
		  if(System.getProperty("testing") != null && System.getProperty("testing").contentEquals("false")) {
				cfg=new HikariConfig(configFile);
				ds=new HikariDataSource(cfg);			
			    connect = ds.getConnection();
		  }else {
			    connect = DriverManager.getConnection(urlH2,"","");
			}
		}catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
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
