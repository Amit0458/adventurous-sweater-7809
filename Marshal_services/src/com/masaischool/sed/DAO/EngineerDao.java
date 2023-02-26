package com.masaischool.sed.DAO;


import java.util.List;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public interface EngineerDao {
	public boolean engineerLogin(String username, String password) throws SomeThingWrongException;
	public void enigineerLogout();
	public List<Complain> showAssignedProblems(Integer empId) throws SomeThingWrongException, NoRecordFoundException;
	public void updateStatusOfComaplain(Integer complainId, String status) throws SomeThingWrongException, NoRecordFoundException;
	public List<Complain> showAllComplains(Integer empId) throws SomeThingWrongException, NoRecordFoundException;
	public void changePassword(String userName, String oldPassword, String neWpassword) throws SomeThingWrongException, NoRecordFoundException;
	
}
