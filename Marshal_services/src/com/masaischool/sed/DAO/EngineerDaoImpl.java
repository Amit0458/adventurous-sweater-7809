package com.masaischool.sed.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public class EngineerDaoImpl implements EngineerDao {

	@Override
	public List<Complain> showAssignedProblems(Integer empId) throws SomeThingWrongException, NoRecordFoundException {
		
		Connection connection = null;
		List<Complain> list = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT * FROM Complains WHERE Status = 'Panding' AND assign_to = ? ";
			
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setInt(1, empId);
			
			ResultSet rs = prepStatment.executeQuery();
			
			
			if(DbUtility.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No complains assign to you");
			}
			
			list = DbUtility.getAllCompliansFromResultSet(rs);
			
		}catch(SQLException ex) {
			throw new SomeThingWrongException();
		}finally {
			try {
				DbUtility.closeConnection(connection);
			}catch(SQLException ex) {
				throw new SomeThingWrongException();
			}
		}
		return list;
	}
		
	@Override
	public void updateStatusOfComaplain(String complainId, String status, LocalDate date) throws SomeThingWrongException, NoRecordFoundException {

		Connection connection = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String UPDATE_QUERY = "UPDATE Complains SET Status = ?, closing_date = ? WHERE comp_id = ?";
			
			PreparedStatement prepStatment = connection.prepareStatement(UPDATE_QUERY);
			
			prepStatment.setString(1, status);
			prepStatment.setDate(2, Date.valueOf(date));
			prepStatment.setString(3, complainId);
			
			
			int response= prepStatment.executeUpdate();
			
			
			if(response == 0) {
				throw new NoRecordFoundException("No such complain found, please check complainID");
			}
		}catch(SQLException ex) {
			throw new SomeThingWrongException();
		}finally {
			try {
				DbUtility.closeConnection(connection);
			}catch(SQLException ex) {
				throw new SomeThingWrongException();
			}
		}
	}

	@Override
	public List<Complain> showAllComplains(Integer empId) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Complain> list = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT * FROM Complains WHERE assign_to = ?";
			
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setInt(1, empId);
			
			ResultSet rs = prepStatment.executeQuery();
			
			
			if(DbUtility.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No complains assign to you");
			}
			
			list = DbUtility.getAllCompliansFromResultSet(rs);
			
		}catch(SQLException ex) {
			throw new SomeThingWrongException();
		}finally {
			try {
				DbUtility.closeConnection(connection);
			}catch(SQLException ex) {
				throw new SomeThingWrongException();
			}
		}
		return list;
	}

	@Override
	public void changePassword(String userName, String oldPassword, String neWpassword) throws SomeThingWrongException, NoRecordFoundException {
		if(checkOldPassword(userName, oldPassword)) {
			Connection connection = null;
			try {
				//get connection
				connection = DbUtility.getConnecton();
				
				//Prepare query
				String UPDATE_QUERY = "UPDATE Engineers SET password = ? WHERE user_name = ?";
				
				PreparedStatement prepStatment = connection.prepareStatement(UPDATE_QUERY);
				
				prepStatment.setString(1, neWpassword);
				prepStatment.setString(2, userName);
				
				
				int response= prepStatment.executeUpdate();
				
				
				if(response == 0) {
					throw new NoRecordFoundException("No such complain found, please check complainID");
				}
			}catch(SQLException ex) {
				throw new SomeThingWrongException();
			}finally {
				try {
					DbUtility.closeConnection(connection);
				}catch(SQLException ex) {
					throw new SomeThingWrongException();
				}
			}
		}else {
			throw new NoRecordFoundException("Wrong password");
		}
	}
	
	private boolean checkOldPassword(String userName, String password) throws SomeThingWrongException {
		Connection connection = null;
		boolean result = true;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT * FROM Engineers WHERE user_name = ? And password = ? ";
			
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setString(1, userName);
			prepStatment.setString(2, password);
			
			ResultSet rs = prepStatment.executeQuery();
			
			if(DbUtility.isResultSetEmpty(rs)) {
				result = false;
			}
			
		}catch(SQLException ex) {
			throw new SomeThingWrongException();
		}finally {
			try {
				DbUtility.closeConnection(connection);
			}catch(SQLException ex) {
			}
		}
		
		return result;
	}
	
		public static void main(String[] args) {
		
		EngineerDao hod = new EngineerDaoImpl();
		
		try {
			
			List<Complain> list  =  hod.showAllComplains(2);
//			hod.changePassword("RBR0678SWE","raghbir0678","raghbir6789");
			list.forEach(i -> System.out.println(i));
		}catch(Exception ex) {
			System.out.println(ex);
		}
		
		
	}
	
}
