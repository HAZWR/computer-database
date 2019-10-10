package org.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
	// URL de connexion
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	// Nom du user
	private static String user = "admincdb";
	// Mot de passe de l'utilisateur
	private static String passwd = "qwerty1234";
	// Objet Connection
	private static Connection connect;

	// Constructeur privé
	private ConnectionBD() throws SQLException {
		try {
			connect = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
	public static Connection getInstance() throws SQLException {
		if (connect == null) {
			new ConnectionBD();
			System.out.println("INSTANCIATION DE LA CONNEXION SQL ! ");
		} else {
			System.out.println("CONNEXION SQL EXISTANTE ! ");
		}
		return connect;
	}

}
