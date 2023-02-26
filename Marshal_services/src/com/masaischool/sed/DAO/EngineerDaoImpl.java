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
import com.masaischool.sed.UI.Main;

public class EngineerDaoImpl implements EngineerDao {
	
	@Override
	public boolean engineerLogin(String username, String password) throws SomeThingWrongException {
		Connection connection = null;
		boolean result = false;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT * FROM Engineers WHERE user_name = ? AND password = ?";
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setString(1, username);
			prepStatment.setString(2, password);

			
			ResultSet rs = prepStatment.executeQuery();
			
			if(DbUtility.isResultSetEmpty(rs)) {
			
			}else {
				result = true;
				rs.next();
				LoggedINUser.loggedInUSerId = rs.getInt("emp_id");
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
		return result;
	}
	@Override
	public List<Complain> showAssignedProblems(Integer empId) throws SomeThingWrongException, NoRecordFoundException {
		
		Connection connection = null;
		List<Complain> list = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT C.comp_id ID, C.reg_date Registered,"
					+ " C.comp_desc Problem, E2.emp_name RaisedBy,"
					+ " E.emp_name Engineer, C.closing_date closing,"
					+ " C.Status Status FROM Complains C "
					+ "INNER JOIN employees E ON C.assign_to = E.emp_id "
					+ "INNER JOIN Employees E2 ON C.raised_by = E2.emp_id WHERE\r\n"
					+ "C.assign_to = ? && C.status = 'panding'";
			
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
	public void updateStatusOfComaplain(Integer complainId, String status) throws SomeThingWrongException, NoRecordFoundException {

		Connection connection = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String UPDATE_QUERY = "UPDATE Complains SET Status = ?, closing_date = ? WHERE comp_id = ?";
			
			PreparedStatement prepStatment = connection.prepareStatement(UPDATE_QUERY);
			
			prepStatment.setString(1, status);
			prepStatment.setDate(2, Date.valueOf(LocalDate.now()));
			prepStatment.setInt(3, complainId);
			
			
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
			String SELECT_QUERY = "SELECT C.comp_id ID, "
					+ "C.reg_date Registered, C.comp_desc Problem, "
					+ "E2.emp_name RaisedBy, E.emp_name Engineer, "
					+ "C.closing_date closing, C.Status Status FROM Complains C "
					+ "INNER JOIN employees E ON C.assign_to = E.emp_id "
					+ "INNER JOIN Employees E2 ON C.raised_by = E2.emp_id WHERE\r\n"
					+ "C.assign_to = ?";
			
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
			Connection connection = null;
			try {
				//get connection
				connection = DbUtility.getConnecton();
				
				String SELECT_QUERY = "SELECT * FROM Engineers WHERE user_name = ? And password = ? ";
				
				PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
				
				//Prepare query
				prepStatment.setString(1, userName);
				prepStatment.setString(2, oldPassword);
				
				
				ResultSet rs = prepStatment.executeQuery();
				if(!DbUtility.isResultSetEmpty(rs)) {
					
				String UPDATE_QUERY = "UPDATE Engineers SET password = ? WHERE user_name = ?";
				
				prepStatment = connection.prepareStatement(UPDATE_QUERY);
				
				prepStatment.setString(1, neWpassword);
				prepStatment.setString(2, userName);
				
				
				int response = prepStatment.executeUpdate();
				
				if(response == 0) {
					throw new NoRecordFoundException("No such complain found, please check complainID");
				}
				}else {
					throw new NoRecordFoundException("Wrong password");
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
	public void enigineerLogout() {
		LoggedINUser.loggedInUSerId = 0;
		Main.mainMenu();
	}
}
