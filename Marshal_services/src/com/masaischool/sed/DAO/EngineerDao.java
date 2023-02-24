package com.masaischool.sed.DAO;

import java.time.LocalDate;
import java.util.List;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public interface EngineerDao {
	public List<Complain> showAssignedProblems(Integer empId) throws SomeThingWrongException, NoRecordFoundException;
	public void updateStatusOfComaplain(String complainId, String status, LocalDate date) throws SomeThingWrongException, NoRecordFoundException;
	public List<Complain> showAllComplains(Integer empId) throws SomeThingWrongException, NoRecordFoundException;
	public void changePassword(String userName, String oldPassword, String neWpassword) throws SomeThingWrongException, NoRecordFoundException;
	
}
