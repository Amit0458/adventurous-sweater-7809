package com.masaischool.sed.UI;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masaischool.sed.DAO.HodDao;
import com.masaischool.sed.DAO.HodDaoImpl;
import com.masaischool.sed.DAO.LoggedINUser;
import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.Engineer;
import com.masaischool.sed.DTO.EngineerImpl;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public class HodUI {
	private Scanner sc;
	private HodDao HOD;
	  
	public HodUI(Scanner sc) {
		this.sc = sc;
		this.HOD = new HodDaoImpl();
	}
	
	public void HodLogin() {
		System.out.println("Enter username ");
		String username = sc.next();
		System.out.println("Enter password ");
		String password = sc.next();
		try {
			if(HOD.hodLogin(username,password)) {
				Main.HodMenu(sc);
			}else {
				System.out.println("Wrong Credentials");
			}
		}catch(SomeThingWrongException | InputMismatchException ex) {
			
		}
	}
	
	public void addEngineer()  {
		
		Engineer engineer = new EngineerImpl();
		
		System.out.println("Enter employee_id");
		engineer.setEmpId(sc.nextInt());
		System.out.println("Enter username");
		engineer.setUserName(sc.next());
		System.out.println("Enter password");
		engineer.setPassword(sc.next());
		System.out.println("Choose category : 1 for Software , 2 for Hardware");
		if(sc.nextInt() == 1) {
			engineer.setCategory("Software");
		}else {
			engineer.setCategory("Hardware");
		}
		try {
			HOD.addNewEngineer(engineer);
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(LoggedINUser.getUserName(engineer.getEmpId()) + " Registered Succesfully, usernmae : " + engineer.getUserName() + ", password : " + engineer.getPassword());
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
		
	}
	
	public void showAllEnginers() {
		try {
			List<Engineer> list = HOD.showAllEnginners();
			System.out.println("----------------------------------------------------------------------------------------------------------");
			list.forEach(System.out :: print);
			System.out.println("----------------------------------------------------------------------------------------------------------");

		}catch(SomeThingWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void deleteEnginner() {
		try {
			System.out.println("Enter Enginner Id");
			HOD.deleteEnginner(sc.nextInt());
			System.out.println("Enginner deleted Succesfully");
		}catch(SomeThingWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void showAllRaisedComplians() {
		try {
			List<Complain> list = HOD.showAllComplins();
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			list.forEach(System.out :: print);
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void showNewRaisedComplians() {
		try {
			List<Complain> list = HOD.showRaisedComplains();
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			list.forEach(System.out :: print);
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}catch(SomeThingWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void assignEnginner() {
		try {
			System.out.println("Enter complain Id");
			Integer compId = sc.nextInt();
			System.out.println("Enter Enginner Id");
			Integer enggId = sc.nextInt();
			HOD.assignEnginner(compId, enggId);
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enginner assigned, Complain Id : "+ compId +" Enginner : " + LoggedINUser.getUserName(enggId) + " Succesfully");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}catch(SomeThingWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void hodLogOut() {
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");
		System.out.println("\t\t\t\t\tBye Bye, " + LoggedINUser.getUserName(LoggedINUser.loggedInUSerId) + " " + Main.grettingMsgforLogout());
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");
		HOD.Hodlogout();
		System.exit(0);
	}
}
