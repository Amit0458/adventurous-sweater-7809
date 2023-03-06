package com.masaischool.sed.UI;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.masaischool.sed.DAO.LoggedINUser;
import com.masaischool.sed.DAO.RegEmployeeDAO;
import com.masaischool.sed.DAO.RegEmployeeSDAOImpl;
import com.masaischool.sed.DTO.RegisterdEmployee;
import com.masaischool.sed.DTO.RegisteredEmployeeImpl;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public class EmployeeRegistrationUI {
	private Scanner sc;
	private RegEmployeeDAO regemployeeDao;
	
	public EmployeeRegistrationUI(Scanner sc) {
		this.sc = sc;
		regemployeeDao = new RegEmployeeSDAOImpl();
	}
	
	public void registerEmployee() {
		Integer empID = 0;
		String username = "";
		String password = "";
		String confirmPassword = "";
		try {
			System.out.println("Please Enter Employee Id");
			empID = sc.nextInt();
			System.out.println("Enter a username");
			username = sc.next()+"";
			System.out.println("Enter new password");
			password = sc.next()+"";
			System.out.println("Confirm password");
			confirmPassword = sc.next()+"";
		}catch(InputMismatchException ex) {
			System.out.println(ex);
			registerEmployee();
		}
		if(password.equals(confirmPassword)) {
			RegisterdEmployee newEmployee = new RegisteredEmployeeImpl();
			newEmployee.setEmp_id(empID);
			newEmployee.setUsername(username);
			newEmployee.setPassword(password);
			newEmployee.setRegdate(LocalDate.now());
			try {
				if(regemployeeDao.registreEmployee(newEmployee)) {
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(LoggedINUser.getUserName(empID) + " Registered Succesfully, With usernmae : " + username + " and password : " + password);
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
				}
				Main.mainMenu();
			}catch(SomeThingWrongException ex) {
				System.out.println("User Name must be unique");
				registerEmployee();
			}
		}else {
			System.out.println("Please check password");
			registerEmployee();
		}	
	}
}
