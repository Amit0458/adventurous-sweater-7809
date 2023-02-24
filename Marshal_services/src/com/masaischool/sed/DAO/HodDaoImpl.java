package com.masaischool.sed.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.ComplainImpl;
import com.masaischool.sed.DTO.Engineer;
import com.masaischool.sed.DTO.EngineerImpl;
import com.masaischool.sed.DTO.HOD;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public class HodDaoImpl implements HodDao {

	@Override
	public boolean addNewEngineer(Integer empId, String username, String password, String category) throws SomeThingWrongException {
		Connection connection = null;
		boolean result = false;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String INSERT_QUERY = "INSERT INTO Engineers (emp_id, user_name, password, category) VALUES (?,?,?,?)";
			PreparedStatement prepStatment = connection.prepareStatement(INSERT_QUERY);
			
			prepStatment.setInt(1, empId);
			prepStatment.setString(2, username);
			prepStatment.setString(3, password);
			prepStatment.setString(4, category);
			
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
	public List<Complain> showRaisedComplains() throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Complain> list = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT * FROM Complains WHERE Status = 'Panding' ";
			
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
	public boolean assignEnginner(Integer complian_id, int eng_id) throws SomeThingWrongException, NoRecordFoundException {
	Connection connection = null;
	boolean result = false;
	try {
		//get connection
		connection = DbUtility.getConnecton();
		
		//Prepare query
		String SELECT_QUERY = "UPDATE Engineers SET assign_to = ? WHERE comp_id = ?";
		
		PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
		
		prepStatment.setInt(1, complian_id);
		prepStatment.setInt(2, eng_id);
		
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

}
