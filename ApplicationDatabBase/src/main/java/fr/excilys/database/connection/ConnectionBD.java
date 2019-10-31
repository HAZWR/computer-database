package fr.excilys.database.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class ConnectionBD {
	private static Connection connect;
	private String configFile= "/db.properties";
	private HikariConfig cfg=new HikariConfig(configFile);
	private HikariDataSource ds=new HikariDataSource(cfg);

	public Connection getConnection(){
		try {	
			    connect = ds.getConnection();
		}catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return connect;
	}
//	public static Connection getInstance() throws SQLException {
//		if (connect == null || connect.isClosed()) {
//			new ConnectionBD();
//			System.out.println("INSTANCIATION DE LA CONNEXION SQL ! ");
//		} else {
//			System.out.println("CONNEXION SQL EXISTANTE ! ");
//		}
//		return connect;
//	}
	
	public static void  closeConnection() {
       if(connect !=null) {
    	   try {
			connect.close();
		} catch (SQLException e) {
			for(Throwable es : e) {
                System.err.println("Erreurs : " + es);
            }
		}
       }	
	}

}
