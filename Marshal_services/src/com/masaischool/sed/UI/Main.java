package com.masaischool.sed.UI;

import java.time.LocalTime;
import java.util.Scanner;

import com.masaischool.sed.DAO.HodDao;
import com.masaischool.sed.DAO.HodDaoImpl;
import com.masaischool.sed.DTO.EngineerImpl;
import com.masaischool.sed.Exceptions.SomeThingWrongException;


public class Main {
	static HodDao hod =  new HodDaoImpl();
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
		System.out.println("5. Assign Compllain to Enginner");
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
	
	static void HodLogin(Scanner sc) {
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
		System.out.println("Welcome " + grettingMsg());
		do {
			
			displayHODMenu();
			System.out.println("Enter selection ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 0:
					System.out.println("Thank you, Have a nice day");
					break;
				case 1:
//					try {
//						
//					}catch(SomeThingWrongException ex) {
//						
//					}
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		
		}while(choice != 0);
		
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println(" 1. Hod Login \n 2. Enginner Login \n 3. Employee Login \n 4. Employee Regitration \n 0. Exit ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 0:
					System.out.println("Thank you, Visit again");
					break;
				case 1:
					HodLogin(sc);
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
		
		
		sc.close();

	}

}
