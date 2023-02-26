package com.masaischool.sed.DAO;

import java.util.List;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.Engineer;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public interface HodDao {
	public boolean hodLogin(String username, String password) throws SomeThingWrongException;
	public void Hodlogout();
	public boolean addNewEngineer(Engineer eng) throws SomeThingWrongException;
	public List<Engineer> showAllEnginners() throws SomeThingWrongException,NoRecordFoundException;
	public boolean deleteEnginner(Integer eng_id) throws SomeThingWrongException,NoRecordFoundException;
	public List<Complain> showRaisedComplains() throws SomeThingWrongException, NoRecordFoundException;
	public List<Complain> showAllComplins() throws SomeThingWrongException, NoRecordFoundException;
	public boolean assignEnginner(Integer complian_id, Integer eng_id) throws SomeThingWrongException, NoRecordFoundException;
}
