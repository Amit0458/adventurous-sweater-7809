package com.masaischool.sed.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbUtility {
	final static String url;
	final static String username;
	final static String password;
	
	static {
		ResourceBundle bundle = null;
		//Load Driver class
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		
		try {
			//Loading bundle object
			bundle = ResourceBundle.getBundle("DbDetails");
		}catch(Exception ex) {
			System.out.println("Please check credenitials file, unable to load");
		}
		
		//Initializing fields 
		url = bundle.getString("url");
		username = bundle.getString("username");
		password = bundle.getString("password");
		
	}
	
	static Connection getConnecton() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	static void closeConnection(Connection object) throws SQLException {
		if(object != null) object.close();
	}
}
