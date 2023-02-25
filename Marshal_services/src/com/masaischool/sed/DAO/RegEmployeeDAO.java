package com.masaischool.sed.DAO;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.Engineer;
import com.masaischool.sed.DTO.RegisterdEngineer;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public interface RegEmployeeDAO {
	public void registreEmployee(RegisterdEngineer eng) throws SomeThingWrongException;
	public void employeeLogin(String username, String password) throws SomeThingWrongException, NoRecordFoundException;
	public void registerComplain(Complain comp) throws SomeThingWrongException;
	
	
}
