package com.masaischool.sed.DAO;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.Engineer;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public class EmployeeSDAOImpl implements EmployeeDAO {

	@Override
	public void registreEmployee(Engineer eng) throws SomeThingWrongException {

	}

	@Override
	public void employeeLogin(String username, String password) throws SomeThingWrongException, NoRecordFoundException {
	
	}

	@Override
	public void registerComplain(Complain comp) throws SomeThingWrongException {
		
	}
	
}
