package org.esgi.web.framework.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DatabaseConnect {

	/**
	 * URL de connection
	 */
	private static String url = "jdbc:mysql://localhost:3306/jwf";
	/**
	 * Nom du user
	 */
	private static String user = "root";
	/**
	 * Mot de passe du user
	 */
	private static String passwd = "";
	/**
	 * Objet Connection
	 */
	private static Connection connect;
	
	/**
	 * Méthode qui va nous retourner notre instance
	 * et la créer si elle n'existe pas...
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static Connection getInstance(){
		if(connect == null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = (Connection) DriverManager.getConnection(url, user, passwd);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return connect;	
	}	
}

