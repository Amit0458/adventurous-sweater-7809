package com.masaischool.sed.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.ComplainImpl;
import com.masaischool.sed.DTO.Engineer;
import com.masaischool.sed.DTO.EngineerImpl;
import com.masaischool.sed.DTO.HOD;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;
import com.masaischool.sed.UI.Main;

public class HodDaoImpl implements HodDao {

	@Override
	public boolean hodLogin(String username, String password) throws SomeThingWrongException {
		Connection connection = null;
		boolean result = false;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT * FROM HOD WHERE email = ? AND password = ?";
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
	public void Hodlogout(Scanner sc) {
		LoggedINUser.loggedInUSerId = 0;
		Main.mainMenu(sc);
	}
	@Override
	public boolean addNewEngineer(Engineer eng) throws SomeThingWrongException {
		Connection connection = null;
		boolean result = false;
		if(isEnigeerAlreadyRegistered(eng.getEmpId())) {
			return false;
		}
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String INSERT_QUERY = "INSERT INTO Engineers (emp_id, user_name, password, category) VALUES (?,?,?,?)";
			PreparedStatement prepStatment = connection.prepareStatement(INSERT_QUERY);
			
			prepStatment.setInt(1, eng.getEmpId());
			prepStatment.setString(2, eng.getUserName());
			prepStatment.setString(3, eng.getPassword());
			prepStatment.setString(4, eng.getCategory());
			
			int respons = prepStatment.executeUpdate();
			
			if(respons <= 0) {
				throw new SomeThingWrongException();
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
	
	private List<Engineer> getAllEnginnersFromResultSet(ResultSet rs) throws SQLException  {
		List<Engineer> list = new ArrayList<>();
		
		while(rs.next()) {
			Engineer eng = new EngineerImpl();
			
			eng.setEmpId(rs.getInt("emp_id"));
			eng.setUserName(rs.getString("user_name"));
			eng.setPassword(rs.getString("password"));
			eng.setCategory(rs.getString("category"));
			
			list.add(eng);
		}
		
		
		return list;
	}
	
	@Override
	public List<Engineer> showAllEnginners() throws SomeThingWrongException,NoRecordFoundException {
		Connection connection = null;
		List<Engineer> list = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT * FROM Engineers";
			
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			ResultSet rs = prepStatment.executeQuery();
			
			if(DbUtility.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No enginners registerd");
			}
			list = getAllEnginnersFromResultSet(rs);
			
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
	public boolean deleteEnginner(Integer eng_id) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		boolean result = false;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "DELETE FROM Engineers WHERE emp_id = ?";
			
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setInt(1, eng_id);
			
			int response = prepStatment.executeUpdate();
			
			if(response == 0) {
				throw new NoRecordFoundException("No such engineer found");
			}
			
			result = true;
			
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
	public List<Complain> showAllComplins() throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Complain> list = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			
			String SELECT_QUERY = "SELECT C.comp_id ID, C.reg_date Registered,"
					+ " C.comp_desc Problem, E2.emp_name RaisedBy, "
					+ "E.emp_name Engineer, C.closing_date closing, "
					+ "C.Status Status FROM Complains C INNER JOIN employees E "
					+ "ON C.assign_to = E.emp_id INNER JOIN Employees E2 ON C.raised_by = E2.emp_id";
			
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			ResultSet rs = prepStatment.executeQuery();
			
			if(DbUtility.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No such engineer found");
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
	public List<Complain> showRaisedComplains() throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Complain> list = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			
			String SELECT_QUERY = "SELECT comp_id ID, reg_date Registered, "
					+ "comp_desc Problem, raised_by RaisedBy, "
					+ "assign_to Engineer, closing_date closing, "
					+ "Status FROM Complains WHERE assign_to is null;";
			
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			ResultSet rs = prepStatment.executeQuery();
			
			if(DbUtility.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No such engineer found");
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
	public boolean assignEnginner(Integer complian_id, Integer eng_id) throws SomeThingWrongException, NoRecordFoundException {
	Connection connection = null;
	boolean result = false;
	try {
		//get connection
		connection = DbUtility.getConnecton();
		
		//Prepare query
		String SELECT_QUERY = "UPDATE Complains SET assign_to = ? WHERE comp_id = ?";
		
		PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
		
		prepStatment.setInt(1, eng_id);
		prepStatment.setInt(2, complian_id);
		
		
		int response = prepStatment.executeUpdate();
		
		if(response == 0) {
			throw new NoRecordFoundException("No such engineer found");
		}
		
		result = true;
		
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

	private boolean isEnigeerAlreadyRegistered(Integer empId) {
		Connection connection = null;
		boolean result = false;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT * FROM Engineers WHERE emp_id = ? ";
			
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setInt(1, empId);
			
			ResultSet rs = prepStatment.executeQuery();
			
			
			if(!DbUtility.isResultSetEmpty(rs)) {
				result = true;
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
	
}
