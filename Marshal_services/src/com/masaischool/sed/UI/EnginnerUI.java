package com.masaischool.sed.UI;

import java.util.List;
import java.util.Scanner;

import com.masaischool.sed.DAO.EngineerDao;
import com.masaischool.sed.DAO.EngineerDaoImpl;
import com.masaischool.sed.DAO.LoggedINUser;
import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;

public class EnginnerUI {
	private Scanner sc;
	private EngineerDao engineer;
	
	public EnginnerUI(Scanner sc) {
		this.sc = sc;
		this.engineer = new EngineerDaoImpl();
	}
	
	public void enginnerLogin() {
		System.out.println("Enter username ");
		String username = sc.next();
		System.out.println("Enter password ");
		String password = sc.next();
		try {
			if(engineer.engineerLogin(username, password)) {
				Main.EngineerMenu(sc);
			}else {
				System.out.println("Wrong Credentials");
			}
		}catch(SomeThingWrongException ex) {
			
		}
	}
	
	public void showAssignedComplains() {
		try {
			List<Complain> list = engineer.showAssignedProblems(LoggedINUser.loggedInUSerId);
			list.forEach(System.out :: println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void updateStatus() {
		System.out.println("Enter complain ID");
		Integer compID = sc.nextInt();
		System.out.println("Choose status \n1. Complete \n2. Working");
		String status = "";
		if(sc.nextInt() == 1) {
			status = "Complete";
		}else {
			status = "Working";
		}
		
		try {
			engineer.updateStatusOfComaplain(compID, status);
			System.out.println("Status Updated");
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void showAllComplains() {
		try {
			List<Complain> list = engineer.showAllComplains(LoggedINUser.loggedInUSerId);
			list.forEach(System.out :: println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
}
