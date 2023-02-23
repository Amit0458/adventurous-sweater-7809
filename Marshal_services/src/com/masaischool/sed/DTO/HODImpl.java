package com.masaischool.sed.DTO;

import java.util.List;
import java.util.Objects;

public class HODImpl implements HOD {
	private Integer emp_id;
	private String name;
	private String email;
	private String password;
	private String incharge_of;
	private List<Employee> employeeList;
	
	public HODImpl() {};
	
	public HODImpl(Integer emp_id, String name, String email, String password, String incharge_of) {
		this.emp_id = emp_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.incharge_of = incharge_of;
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

	public String getIncharge_of() {
		return incharge_of;
	}

	public void setIncharge_of(String incharge_of) {
		this.incharge_of = incharge_of;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
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
		HODImpl other = (HODImpl) obj;
		return Objects.equals(emp_id, other.emp_id);
	}

	@Override
	public String toString() {
		return "HOD Employee_id = " + emp_id + ", Name = " + name + ", Email = " + email + ", Password = " + password
				+ ", Incharge_of = " + incharge_of + ", Employee List = " + employeeList + "\n";
	}
	
	
	
	
}
