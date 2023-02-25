package com.masaischool.sed.UI;

import java.time.LocalTime;
import java.util.Scanner;

import com.masaischool.sed.DAO.EngineerDao;
import com.masaischool.sed.DAO.HodDao;
import com.masaischool.sed.DAO.HodDaoImpl;
import com.masaischool.sed.DAO.LoggedINUser;
import com.masaischool.sed.DTO.EngineerImpl;
import com.masaischool.sed.Exceptions.SomeThingWrongException;


public class Main {
	
	private static HodUI hodUi;
	private static EnginnerUI engineerUi;
	public static Scanner sc;
	
	static String grettingMsg() {
			LocalTime time = LocalTime.now();
			int hours = 0;
			hours = time.getHour();
	        if(hours>=1 && hours<=12){
	            return "Good Mooring"; 
	        }else if(hours>=12 && hours<=16){
	            return "Good Afternoon";
	        }else if(hours>=16 && hours<=21){
	            return "Good Evening";
	        }else if(hours>=21 && hours<=24){
	           return "Good Night";
	        }else {
	        	return null;
	        }
	}
	
	static void displayHODMenu() {
		System.out.println("1. Register a new Engineer");
		System.out.println("2. View all Engineers");
		System.out.println("3. Delete a Engineer");
		System.out.println("4. View all raise complains");
		System.out.println("5. View new raise complains");
		System.out.println("6. Assign Compllain to Enginner");
		System.out.println("7. Log out");
	}
	static void displayEngineerMenu() {
		System.out.println("1. View assigned complains");
		System.out.println("2. Update status of complain");
		System.out.println("3. view all complains");
		System.out.println("4. Change Password");
	}
	static void displayEmployeeMenu() {
		System.out.println("1. Register a new complian");
		System.out.println("2. Check status of complain");
		System.out.println("3. View all raise complains");
		System.out.println("4. Change password");
	}
	
	static void adminLogin(Scanner sc) {
		System.out.println("Enter username ");
		String username = sc.next();
		System.out.println("Enter password ");
		String password = sc.next();
		
		if(username.compareToIgnoreCase("admin") == 0 && password.compareTo(password) == 0) {
			HodMenu(sc);
		}else {
			System.out.println("Wrong username password");
		}
		
	}
	static void HodMenu(Scanner sc) {
		int choice = 0;
		System.out.println("Welcome " + LoggedINUser.getUserName(LoggedINUser.loggedInUSerId) + " " + grettingMsg());
		do {
			
			displayHODMenu();
			System.out.println("Enter selection ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 0:
					System.out.println("Thank you, Have a nice day");
					break;
				case 1:
					hodUi.addEngineer();
					break;
				case 2:
					hodUi.showAllEnginers();
					break;
				case 3:
					hodUi.deleteEnginner();
					break;
				case 4:
					hodUi.showAllRaisedComplians();
					break;
				case 5:
					hodUi.showNewRaisedComplians();
					break;
				case 6:
					hodUi.assignEnginner();
					break;
				case 7:
					hodUi.hodLogOut();;
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		
		}while(choice != 0);
		
	}
	
	static void EngineerMenu(Scanner sc) {
		int choice = 0;
		System.out.println("Welcome " + LoggedINUser.getUserName(LoggedINUser.loggedInUSerId) + " " + grettingMsg());
		do {
			
			displayEngineerMenu();
			System.out.println("Enter selection ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 0:
					System.out.println("Thank you, Have a nice day");
					break;
				case 1:
					engineerUi.showAssignedComplains();
					break;
				case 2:
					engineerUi.updateStatus();
					break;
				case 3:
					engineerUi.showAllComplains();
					break;
				case 4:
					
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		
		}while(choice != 0);
		
	}
	
	public static void mainMenu(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1. Hod Login \n2. Enginner Login \n3. Employee Login \n4. Employee Regitration \n0. Exit ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 0:
					System.out.println("Thank you, Visit again");
					break;
				case 1:
					hodUi.HodLogin();
					break;
				case 2:
					engineerUi.enginnerLogin();
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
	}
	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		hodUi = new HodUI(sc);
		engineerUi = new EnginnerUI(sc);
		
		mainMenu(sc);
		
		sc.close();

	}

}
