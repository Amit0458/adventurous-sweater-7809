package com.masaischool.sed.UI;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masaischool.sed.DAO.LoggedINUser;
import com.masaischool.sed.DAO.RegEmployeeDAO;
import com.masaischool.sed.DAO.RegEmployeeSDAOImpl;
import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.ComplainImpl;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public class EmployeeUI {
	private Scanner sc;
	private RegEmployeeDAO regemployeeDao;
	
	public EmployeeUI(Scanner sc) {
		this.sc = sc;
		regemployeeDao = new RegEmployeeSDAOImpl();
	}
	
	public void employeeLogin() {
		System.out.println("Enter username ");
		String username = sc.next();
		System.out.println("Enter password ");
		String password = sc.next();
		try {
			if(regemployeeDao.employeeLogin(username, password)) {
				Main.EmployeMenu();
			}else {
				System.out.println("Wrong Credentials");
			}
		}catch(SomeThingWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void registerComplain() {
		String input = "";
		String problem = "";
		System.out.println("Enter problem ");
		try {
			input = sc.next();
			System.out.println("Enter # to register");
			while(!input.equals("#")) {
				problem += input+" ";
				input = sc.next();
			}
		}catch(InputMismatchException ex) {
			System.out.println("Please enter Coorect problem");
		}
		
		Complain newComplain = new ComplainImpl();
		
		newComplain.setComplain_desc(problem);
		newComplain.setRegister_Date(LocalDate.now());
		newComplain.setRaised_by(LoggedINUser.loggedInUSerId);
		newComplain.setComplain_id(++LoggedINUser.complainId);
		
		try {
			regemployeeDao.registerComplain(newComplain);
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println("Complaine registered succesfully, With complian ID : " + newComplain.getComplain_id());
			System.out.println("----------------------------------------------------------------------------------------------");

		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
		
	}
	
	public void checkStatus() {
		try {
			System.out.println("Enter complian ID");
			Integer complainId = sc.nextInt();
			System.out.println(regemployeeDao.checkStatus(complainId)); 
		}catch(SomeThingWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void showAllComplains() {
		try {
			List<Complain> list = regemployeeDao.showAllComplain();
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			list.forEach(System.out :: print);
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void changePassword() {
		String username = "";
		String olsPassword = "";
		String newPassword = ""; 
		try {
			System.out.println("Enter username ");
			username= sc.next();
			System.out.println("Enter old password ");
			olsPassword = sc.next();
			System.out.println("Enter new password ");
			newPassword = sc.next();
			System.out.println("Confirm password ");
		}catch(InputMismatchException ex) {
			
		}
		if(newPassword.equals(sc.next())) {
			try {
				regemployeeDao.changePassword(username, olsPassword, newPassword);
				System.out.println("Password changed Succesfully");
			}catch(SomeThingWrongException | NoRecordFoundException ex) {
				System.out.println(ex);
			}
		}else {
			System.out.println("Password doens't matches");
			changePassword();
		}
		
	}
	
	public void engineerLogOut() {
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");
		System.out.println("\t\t\t\t\tBye Bye, " + LoggedINUser.getUserName(LoggedINUser.loggedInUSerId) + " " + Main.grettingMsgforLogout());
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");
		regemployeeDao.enigineerLogout();
		System.exit(0);
	}
	
}
