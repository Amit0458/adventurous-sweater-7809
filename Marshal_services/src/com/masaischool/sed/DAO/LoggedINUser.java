package com.masaischool.sed.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoggedINUser {
	public static int loggedInUSerId;
	
	public static String getUserName(Integer empId) { 

		String result = "";
		Connection connection = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT emp_name FROM Employees WHERE emp_id = ?";
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setInt(1, empId);

			
			ResultSet rs = prepStatment.executeQuery();
			
			if(DbUtility.isResultSetEmpty(rs)) {
			
			}else {
				rs.next();
				result = rs.getString("emp_name");
			}
			
		}catch(SQLException ex) {
		}finally {
			try {
				DbUtility.closeConnection(connection);
			}catch(SQLException ex) {
			}
			
		}
		return result;
	}
	
	public static Integer complainId = 0;
	
	static {
		
		Connection connection = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT MAX(comp_id) ID FROM Complains";
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
		
			ResultSet rs = prepStatment.executeQuery();
			
			if(DbUtility.isResultSetEmpty(rs)) {
				
			}else {
				rs.next();
				complainId = rs.getInt("ID");
			}
			
		}catch(SQLException ex) {
		}finally {
			try {
				DbUtility.closeConnection(connection);
			}catch(SQLException ex) {
			}
			
		}
	}
	
}
