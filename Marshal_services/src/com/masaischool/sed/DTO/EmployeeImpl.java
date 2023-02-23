package com.masaischool.sed.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class EmployeeImpl implements Employee {
	private Integer emp_id;
	private String name;
	private String email;
	private String password;
	private Department department;
	private String job_name;
	private Integer manager_id;
	private LocalDate joining_data;
	private Double salary;
	private String country;
	private String std_code;
	
	
	public EmployeeImpl() {};
	
	public EmployeeImpl(Integer emp_id, String name, String email, String password,  Department department, String job_name,
			Integer manager_id, LocalDate joining_data, Double salary, String country,
			String std_code) {
		this.emp_id = emp_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.department = department;
		this.job_name = job_name;
		this.manager_id = manager_id;
		this.joining_data = joining_data;
		this.salary = salary;
		this.country = country;
		this.std_code = std_code;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepetment() {
		return department;
	}

	public void setDepetment(Department depetment) {
		this.department = depetment;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public Integer getManager_id() {
		return manager_id;
	}

	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}

	public LocalDate getJoining_data() {
		return joining_data;
	}

	public void setJoining_data(LocalDate joining_data) {
		this.joining_data = joining_data;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStd_code() {
		return std_code;
	}

	public void setStd_code(String std_code) {
		this.std_code = std_code;
	}

	@Override
	public int hashCode() {
		return emp_id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeImpl other = (EmployeeImpl) obj;
		return Objects.equals(emp_id, other.emp_id);
	}

	@Override
	public String toString() {
		return "Employee_id = " + emp_id + ", Name = " + name + ", Email = " + email + ", Password = " + password
				+ ", Depetment = " + department + ", job_name = " + job_name + ", Manager_id = " + manager_id
				+ ", Joining _ data = " + joining_data + ", Salary = " + salary + " , Country = "
				+ country + ", Std_code = " + std_code + "\n";
	}
	
	
	
	
	
}
