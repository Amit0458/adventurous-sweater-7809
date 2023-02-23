package com.masaischool.sed.DTO;

public class DepartmentImpl implements Department {
	private Integer department_id;
	private String department_name;
	
	public DepartmentImpl() {};
	
	public DepartmentImpl(Integer department_id, String department_name) {
		this.department_id = department_id;
		this.department_name = department_name;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	
}
