package com.masaischool.sed.DTO;

import java.time.LocalDate;

public interface Employee {
	public Integer getEmp_id();

	public void setEmp_id(Integer emp_id);

	public String getName();

	public void setName(String name);

	public String getEmail();

	public void setEmail(String email);

	public String getPassword();

	public void setPassword(String password);

	public Department getDepetment();

	public void setDepetment(Department depetment);

	public String getJob_name();

	public void setJob_name(String job_name);

	public Integer getManager_id();

	public void setManager_id(Integer manager_id);

	public LocalDate getJoining_data();

	public void setJoining_data(LocalDate joining_data);

	public Double getSalary();

	public void setSalary(Double salary);

	public String getCountry();

	public void setCountry(String country);

	public String getStd_code();

	public void setStd_code(String std_code);
}
