package com.masaischool.sed.DTO;

import java.time.LocalDate;

public interface Complain {
	public Integer getComplain_id();
	public void setComplain_id(Integer complain_id);
	public LocalDate getRegister_Date();
	public void setRegister_Date(LocalDate register_Date);
	public LocalDate getClosing_date();
	public void setClosing_date(LocalDate closing_date);
	public String getComplain_desc();
	public void setComplain_desc(String complain_desc);
	public String getComplain_status();
	public void setComplain_status(String complain_status);
	public Engineer getAssigned_eng();
	public String getEnggName();
	public void setEnggName(String enggName);
	public String getEmployeeName();
	public void setEmployeeName(String employeeName);
	public void setAssigned_eng(Engineer assigned_eng);
	public Employee getRaised_by();
	public void setRaised_by(Employee raised_by);
}
