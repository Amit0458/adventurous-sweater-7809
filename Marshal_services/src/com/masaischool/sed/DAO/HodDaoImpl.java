package com.masaischool.sed.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.Engineer;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public class HodDaoImpl implements HodDao {

	@Override
	public boolean addNewEngineer(Engineer eng) throws SomeThingWrongException {
		Connection connection = null;
		boolean result = false;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			String INSERT_QUERY = "INSERT INTO Engineers (emp_id, eng_name, user_name, password, category) VALUES (?,?,?,?,?)";
			PreparedStatement prepStatment = connection.prepareStatement(INSERT_QUERY);
			
			prepStatment.setInt(1, 1);
			prepStatment.setString(2, "Raghbir Singh Bhamrah");
			prepStatment.setString(3, "RBR0678SWE");
			prepStatment.setString(4, "raghbir0678");
			prepStatment.setString(5, "Software");
			
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

	@Override
	public List<Engineer> showAllEnginners() throws SomeThingWrongException {
		return null;
	}

	@Override
	public boolean deleteEnginner(Integer eng_id) throws SomeThingWrongException, NoRecordFoundException {
		return false;
	}

	@Override
	public List<Complain> showRaisedComplains() throws SomeThingWrongException, NoRecordFoundException {
		return null;
	}

	@Override
	public boolean assignEnginner(Integer complian_id, int eng_id)
			throws SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	

}
