package com.masaischool.sed.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.ComplainImpl;

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
	
	static boolean isResultSetEmpty(ResultSet resultSet) throws SQLException {

		return (!resultSet.isBeforeFirst() && resultSet.getRow() == 0) ? true : false;
	}
	static List<Complain> getAllCompliansFromResultSet(ResultSet rs) throws SQLException  {
		List<Complain> list = new ArrayList<>();
		
		while(rs.next()) {
			Complain comp = new ComplainImpl();
			comp.setComplain_id(rs.getInt("ID"));
			comp.setRegister_Date(rs.getDate("Registered").toLocalDate());
			comp.setComplain_desc(rs.getString("Problem"));
			comp.setEmployeeName(rs.getString("RaisedBy"));
			comp.setComplain_status(rs.getString("Status"));
			comp.setEnggName(rs.getString("Engineer"));
			if(rs.getDate("closing") != null)  {
				comp.setClosing_date(rs.getDate("closing").toLocalDate());
			}
			
			
			list.add(comp);
		}
		
		return list;
	}
	
	
}
