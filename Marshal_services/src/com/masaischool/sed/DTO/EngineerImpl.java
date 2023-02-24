package com.masaischool.sed.DTO;

import java.util.Objects;

public class EngineerImpl implements Engineer {
	private Integer empId;
	private String username;
	private String password;
	private String category;
	
	public EngineerImpl() {};
	
	public EngineerImpl(Integer emp_id, String username, String password, String category) {
		this.empId = emp_id;
		this.username = username;
		this.password = password;
		this.category = category;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer emp_id) {
		this.empId = emp_id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String email) {
		this.username = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return empId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EngineerImpl other = (EngineerImpl) obj;
		return Objects.equals(empId, other.empId);
	}

	@Override
	public String toString() {
		return "Employee_id = " + empId + ", username = " + username + ", Password = " + password
				+ ", Category = " + category + "\n";
	}

}
