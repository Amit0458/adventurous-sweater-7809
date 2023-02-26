package com.masaischool.sed.DAO;

import java.util.List;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.RegisterdEmployee;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public interface RegEmployeeDAO {
	public boolean registreEmployee(RegisterdEmployee eng) throws SomeThingWrongException;
	public boolean employeeLogin(String username, String password) throws SomeThingWrongException, NoRecordFoundException;
	public void registerComplain(Complain comp) throws SomeThingWrongException;
	public Complain checkStatus(Integer complainId) throws SomeThingWrongException, NoRecordFoundException;
	public List<Complain> showAllComplain() throws SomeThingWrongException, NoRecordFoundException;
	public void changePassword(String userName, String oldPassword, String neWpassword) throws SomeThingWrongException, NoRecordFoundException;
	public void enigineerLogout();
	
}
