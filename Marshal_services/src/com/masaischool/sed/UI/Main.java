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
		System.out.println("1. Register a new Engineer");
		System.out.println("2. View all Engineers");
		System.out.println("3. Delete a Engineer");
		System.out.println("4. View all raised complains");
		System.out.println("5. View new raised complains");
		System.out.println("6. Assign Compllain to a Enginner");
		System.out.println("7. Log out");
	}
	static void displayEngineerMenu() {
		System.out.println("1. View assigned complains");
		System.out.println("2. Update status of complain");
		System.out.println("3. View all complains");
		System.out.println("4. Change Password");
		System.out.println("5. Log out");
	}
	static void displayEmployeeMenu() {
		System.out.println("1. Register a new complian");
		System.out.println("2. Check status of complain");
		System.out.println("3. View all raised complains");
		System.out.println("4. Change password");
		System.out.println("5. Log out");
	}
	
	static void HodMenu(Scanner sc) {
		int choice = 0;
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");
		System.out.println("\t\t\t\t\tWelcome, " + LoggedINUser.getUserName(LoggedINUser.loggedInUSerId) + " " + grettingMsg()+"!") ;
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");
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
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");
		System.out.println("\t\t\t\t\tWelcome, " + LoggedINUser.getUserName(LoggedINUser.loggedInUSerId) + " " + grettingMsg()+"!");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");

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
					engineerUi.changePassword();
					break;
				case 5:
					engineerUi.engineerLogOut();;
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		
		}while(choice != 0);
		
	}
	
	static void EmployeMenu() {
		int choice = 0;
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");
		System.out.println("\t\t\t\t\tWelcome, " + LoggedINUser.getUserName(LoggedINUser.loggedInUSerId) + " " + grettingMsg()+"!");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-");

		do {
			
			displayEmployeeMenu();
			System.out.println("Enter selection ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 0:
					System.out.println("Thank you, Have a nice day");
					break;
				case 1:
					employeeUi.registerComplain();
					break;
				case 2:
					employeeUi.checkStatus();
					break;
				case 3:
					employeeUi.showAllComplains();
					break;
				case 4:
					employeeUi.changePassword();
					break;
				case 5:
					employeeUi.engineerLogOut();;
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		
		}while(choice != 0);
		
	}
	
	public static void mainMenu() {
		try {
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
						employeeUi.employeeLogin();
						break;
					case 4:
						employeeRegistrtion.registerEmployee();
						break;
					default:
						System.out.println("Invalid Selection, try again");
				}
			}while(choice != 0);
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
