package com.masaischool.sed.DTO;

import java.time.LocalDate;

public interface RegisterdEmployee {
	public Integer getEmp_id();
	public void setEmp_id(Integer emp_id);
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public LocalDate getRegdate();
	public void setRegdate(LocalDate regdate);
}
