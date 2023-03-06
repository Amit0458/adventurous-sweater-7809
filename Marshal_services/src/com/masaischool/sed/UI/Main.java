package com.masaischool.sed.UI;

import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.masaischool.sed.DAO.LoggedINUser;



public class Main {
	public static Scanner sc;
	private static HodUI hodUi;
	private static EnginnerUI engineerUi;
	private static EmployeeUI employeeUi;
	private static EmployeeRegistrationUI employeeRegistrtion;
	
	
	static String grettingMsg() {
			LocalTime time = LocalTime.now();
			int hours = 0;
			hours = time.getHour();
	        if(hours>=1 && hours<=12){
	            return "Good Mooring"; 
	        }else if(hours>=12 && hours<=16){
	            return "Good Afternoon";
	        }else {
	            return "Good Evening";
	        }
	}
	static String grettingMsgforLogout() {
		LocalTime time = LocalTime.now();
		int hours = 0;
		hours = time.getHour();
        if(hours>=19 && hours<=24){
            return "Good Night";
        }else {
           return "Have a nice day!";
        }
	}
	
	static void displayHODMenu() {
		try {
		System.out.println("=-=-=-=--=-=-=-=--=-=--=--=-=-=-=-=-=-=-=-=-=-=-");
		Thread.sleep(200);
		System.out.println("1. Register a new Engineer");
		Thread.sleep(200);
		System.out.println("2. View all Engineers");
		Thread.sleep(200);
		System.out.println("3. Delete a Engineer");
		Thread.sleep(200);
		System.out.println("4. View all raised complains");
		Thread.sleep(200);
		System.out.println("5. View new raised complains");
		Thread.sleep(200);
		System.out.println("6. Assign Compllain to a Enginner");
		Thread.sleep(200);
		System.out.println("7. Log out");
		Thread.sleep(200);
		System.out.println("=-=-=-=--=-=-=-=--=-=--=--=-=-=-=-=-=-=-=-=-=-=-");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	static void displayEngineerMenu()  {
		try {
		System.out.println("=-=-=-=--=-=-=-=--=-=--=--=-=-=-=-=-=-=-=-=-=-=-");
		Thread.sleep(200);
		System.out.println("1. View assigned complains");
		Thread.sleep(200);
		System.out.println("2. Update status of complain");
		Thread.sleep(200);
		System.out.println("3. View all complains");
		Thread.sleep(200);
		System.out.println("4. Change Password");
		Thread.sleep(200);
		System.out.println("5. Log out");
		Thread.sleep(200);
		System.out.println("=-=-=-=--=-=-=-=--=-=--=--=-=-=-=-=-=-=-=-=-=-=-");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	static void displayEmployeeMenu() {
		try {
		System.out.println("=-=-=-=--=-=-=-=--=-=--=--=-=-=-=-=-=-=-=-=-=-=-");
		Thread.sleep(200);
		System.out.println("1. Register a new complian");
		Thread.sleep(200);
		System.out.println("2. Check status of complain");
		Thread.sleep(200);
		System.out.println("3. View all raised complains");
		Thread.sleep(200);
		System.out.println("4. Change password");
		Thread.sleep(200);
		System.out.println("5. Log out");
		Thread.sleep(200);
		System.out.println("=-=-=-=--=-=-=-=--=-=--=--=-=-=-=-=-=-=-=-=-=-=-");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static void HodMenu(Scanner sc) {
		try {
		String choice = "0";
		do {
			displayHODMenu();
			System.out.println();
			System.out.println("Enter selection ");
			System.out.println();
			choice = sc.next();
			
			switch (choice) {
				case "0":
					hodUi.hodLogOut();
					break;
				case "1":
					hodUi.addEngineer();
					break;
				case "2":
					hodUi.showAllEnginers();
					break;
				case "3":
					hodUi.deleteEnginner();
					break;
				case "4":
					hodUi.showAllRaisedComplians();
					break;
				case "5":
					hodUi.showNewRaisedComplians();
					break;
				case "6":
					hodUi.assignEnginner();
					break;
				case "7":
					hodUi.hodLogOut();
					break;
				default:
					System.out.println();
					System.out.println("Invalid Selection, try again");
					System.out.println();
					HodMenu(sc);
			}
		
			}while(choice.equals("0"));
		}catch(InputMismatchException ex) {
			
		}
	}
	
	static void EngineerMenu(Scanner sc) {
		try {
		String choice = "";
		do {
			
			displayEngineerMenu();
			System.out.println();
			System.out.println("Enter selection ");
			System.out.println();
			choice = sc.next();
			
			switch (choice) {
				case "0":
					engineerUi.engineerLogOut();
					break;
				case "1":
					engineerUi.showAssignedComplains();
					break;
				case "2":
					engineerUi.updateStatus();
					break;
				case "3":
					engineerUi.showAllComplains();
					break;
				case "4":
					engineerUi.changePassword();
					break;
				case "5":
					engineerUi.engineerLogOut();
					break;
				default:
					System.out.println();
					System.out.println("Invalid Selection, try again");
					System.out.println();
					EngineerMenu(sc);
			}
		
			}while(choice.equals("0"));
		}catch(InputMismatchException ex) {
			
		}
	}
	
	static void EmployeMenu() {
		try {
			String choice = "";
		do {
			displayEmployeeMenu();
			System.out.println();
			System.out.println("Enter selection ");
			System.out.println();
			choice = sc.next();
			
			switch (choice) {
				case "0":
					employeeUi.engineerLogOut();
					break;
				case "1":
					employeeUi.registerComplain();
					break;
				case "2":
					employeeUi.checkStatus();
					break;
				case "3":
					employeeUi.showAllComplains();
					break;
				case "4":
					employeeUi.changePassword();
					break;
				case "5":
					employeeUi.engineerLogOut();
					break;
				default:
					System.out.println();
					System.out.println("Invalid Selection, try again");
					System.out.println();
					EmployeMenu();
				}
		
			}while(choice.equals("0"));
		}catch(InputMismatchException ex) {
			
		}
	}
	
	public static void mainMenu() {
		try {
			String choice = "";
			do {
				System.out.println("+=-=-=-=--=-=-=-=--=-=--=--=-=-=-=-=-=-=-=-=-=-=+");
				System.out.println("|\t\t1. Hod Login \t\t\t|\n|\t\t2. Enginner Login \t\t|\n|\t\t3. Employee Login \t\t|\n|\t\t4. Employee Regitration \t|\n|\t\t0. Exit \t\t\t|");
				System.out.println("+=-=-=-=--=-=-=-=--=-=--=--=-=-=-=-=-=-=-=-=-=-=+");
				
				System.out.println();
				System.out.println("Enter selection ");
				System.out.println();
				
				choice = sc.next();
				
				switch(choice) {
					case "0":
						System.out.println("Thank you, visit again");
						break;
					case "1":
						hodUi.HodLogin();
						break;
					case "2":
						engineerUi.enginnerLogin();
						break;
					case "3":
						employeeUi.employeeLogin();
						break;
					case "4":
						employeeRegistrtion.registerEmployee();
						break;
					default:
						System.out.println();
						System.out.println("Invalid Selection, try again");
						mainMenu();
						System.out.println();
				}
			}while(choice.equals("0"));
		}catch(InputMismatchException ex) {
			
		}
		
	}
	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		hodUi = new HodUI(sc);
		engineerUi = new EnginnerUI(sc);
		employeeUi = new EmployeeUI(sc);
		employeeRegistrtion = new EmployeeRegistrationUI(sc);
		
		mainMenu();
		
		sc.close();

	}
}
