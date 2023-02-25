package com.masaischool.sed.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.ComplainImpl;
import com.masaischool.sed.DTO.RegisterdEngineer;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public class RegEmployeeSDAOImpl implements RegEmployeeDAO {
	
	@Override
	public void registreEmployee(RegisterdEngineer eng) throws SomeThingWrongException {
		Connection connection = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String INSERT_QUERY = "INSERT INTO regemployees (emp_id, usrename, password, rigister_date) VALUES (?,?,?,?)";
			
			PreparedStatement prepStatment = connection.prepareStatement(INSERT_QUERY);
			
			prepStatment.setInt(1, eng.getEmp_id());
			prepStatment.setString(2, eng.getUsername());
			prepStatment.setString(3, eng.getPassword());
			prepStatment.setDate(4, Date.valueOf(eng.getRegdate()));
			
			int respons = prepStatment.executeUpdate();
			
			if(respons == 0) {
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
	}

	@Override
	public void employeeLogin(String username, String password) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String SELECT_QUERY = "SELECT * FROM regemployees WHERE usrename = ? AND password = ?";
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setString(1, username);
			prepStatment.setString(2, password);

			
			ResultSet rs = prepStatment.executeQuery();
			
			if(DbUtility.isResultSetEmpty(rs)) {
			
			}else {
				rs.next();
				LoggedINUser.loggedInUSerId = rs.getInt("emp_id");
				System.out.println("Loggin Succesful");
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
	public void registerComplain(Complain comp) throws SomeThingWrongException {
		Connection connection = null;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String INSERT_QUERY = "INSERT INTO Complains (reg_date, comp_desc, raised_by) VALUES (?,?,?)";
			
			PreparedStatement prepStatment = connection.prepareStatement(INSERT_QUERY);
			
			prepStatment.setDate(1, Date.valueOf(comp.getRegister_Date()));
			prepStatment.setString(2, comp.getComplain_desc());
			prepStatment.setInt(3, LoggedINUser.loggedInUSerId);
			
			int respons = prepStatment.executeUpdate();
			
			if(respons == 0) {
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
	}
	
	public static void main(String[] args) throws SomeThingWrongException, NoRecordFoundException {
		RegEmployeeDAO rd = new RegEmployeeSDAOImpl();
		
		rd.employeeLogin("test","test");
		
//		rd.registerComplain(new ComplainImpl(2,LocalDate.now(), "dont Know"));
		
		
		
		System.out.println(LoggedINUser.getUserName(LoggedINUser.loggedInUSerId));
		
		System.out.println(LoggedINUser.complainId);
		
//		rd.registreEmployee(new RegisteredEngineerImpl(LoggedINUser.loggedInUSerId,"test", "test",LocalDate.now()));
	
	}
}
