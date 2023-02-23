package com.masaischool.sed.DTO;

import java.util.Objects;

public class EngineerImpl implements Engineer {
	private Integer emp_id;
	private String name;
	private String email;
	private String password;
	private String category;
	
	public EngineerImpl() {};
	
	public EngineerImpl(Integer emp_id, String name, String email, String password, String category) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.category = category;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		EngineerImpl other = (EngineerImpl) obj;
		return Objects.equals(emp_id, other.emp_id);
	}

	@Override
	public String toString() {
		return "Employee_id = " + emp_id + ", Name = " + name + ",Email = " + email + ", Password = " + password
				+ ", Category = " + category + "\n";
	}
}
