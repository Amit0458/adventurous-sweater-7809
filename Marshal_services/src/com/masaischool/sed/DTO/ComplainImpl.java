package com.masaischool.sed.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class ComplainImpl implements Complain {
	private Integer complain_id;
	private LocalDate register_Date;
	private LocalDate closing_date;
	private String complain_desc;
	private String complain_status;
	private String enggName;
	private String employeeName;
	private Engineer assigned_eng;
	private Employee raised_by;
	
	public ComplainImpl() {};
	
	public ComplainImpl(Integer complain_id, LocalDate register_Date, String complain_desc) {
		super();
		this.complain_id = complain_id;
		this.register_Date = register_Date;
		this.complain_desc = complain_desc;
	}

	public Integer getComplain_id() {
		return complain_id;
	}
	
	public void setComplain_id(Integer complain_id) {
		this.complain_id = complain_id;
	}

	public LocalDate getRegister_Date() {
		return register_Date;
	}

	public void setRegister_Date(LocalDate register_Date) {
		this.register_Date = register_Date;
	}

	public LocalDate getClosing_date() {
		return closing_date;
	}

	public void setClosing_date(LocalDate closing_date) {
		this.closing_date = closing_date;
	}

	public String getComplain_desc() {
		return complain_desc;
	}

	public void setComplain_desc(String complain_desc) {
		this.complain_desc = complain_desc;
	}

	public String getComplain_status() {
		return complain_status;
	}

	public void setComplain_status(String complain_status) {
		this.complain_status = complain_status;
	}

	public Engineer getAssigned_eng() {
		return assigned_eng;
	}

	public void setAssigned_eng(Engineer assigned_eng) {
		this.assigned_eng = assigned_eng;
	}
	
	public String getEnggName() {
		return enggName;
	}

	public void setEnggName(String enggName) {
		this.enggName = enggName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Employee getRaised_by() {
		return raised_by;
	}

	public void setRaised_by(Employee raised_by) {
		this.raised_by = raised_by;
	}
	
	

	@Override
	public int hashCode() {
		return complain_id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplainImpl other = (ComplainImpl) obj;
		return Objects.equals(complain_id, other.complain_id);
	}

	@Override
	public String toString() {
		return "ID: " + complain_id + ", Register on: " + register_Date + ", Problem: " + complain_desc + ", Raised By: " + employeeName  +", Assigned to: "+ enggName +", Status: " + complain_status + "\n";
	}

	
	
	
	
	
	
	
}
