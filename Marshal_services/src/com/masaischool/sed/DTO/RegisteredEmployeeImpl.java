package com.masaischool.sed.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class RegisteredEmployeeImpl implements RegisterdEmployee {

	private Integer emp_id;
	private String username;
	private String password;
	private LocalDate regdate;
	
	public RegisteredEmployeeImpl() {};
	
	public RegisteredEmployeeImpl(Integer emp_id, String username, String password, LocalDate regdate) {
		this.emp_id = emp_id;
		this.username = username;
		this.password = password;
		this.regdate = regdate;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDate regdate) {
		this.regdate = regdate;
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
		RegisteredEmployeeImpl other = (RegisteredEmployeeImpl) obj;
		return Objects.equals(emp_id, other.emp_id);
	}

	@Override
	public String toString() {
		return " ID " + emp_id + ", User Name : " + username + ", Password : " + password
				+ "\n";
	}
	
	
	
	
}
