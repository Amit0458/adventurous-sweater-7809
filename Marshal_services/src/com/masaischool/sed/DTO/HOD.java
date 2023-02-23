package com.masaischool.sed.DTO;

import java.util.List;

public interface HOD {
	public Integer getEmp_id();

	public void setEmp_id(Integer emp_id);

	public String getName();

	public void setName(String name);

	public String getEmail();

	public void setEmail(String email);

	public String getPassword();

	public void setPassword(String password);

	public String getIncharge_of();

	public void setIncharge_of(String incharge_of);

	public List<Employee> getEmployeeList();

	public void setEmployeeList(List<Employee> employeeList);
}
