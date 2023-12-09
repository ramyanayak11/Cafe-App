/*
 * Name: RAMYA NAYAK
 * 
 * Course: CS 151
 * Final Project
 * Date: 12/08/2023
 * 
 */

import java.util.Scanner;

public class User {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String user = determineUser();
		Process menuManager = new Process();
		String again;
		
		if (user == "customer") {
			Customer some = new Customer(menuManager);
			some.order();
		}
		else if (user == "staff") {
			Staff other = new Staff(menuManager);
			other.prompt();
		}
		else if (user == null) {
			return;
		}
		
		do {
			System.out.println();
			System.out.print("Would you like to log in to the application again? (yes/no): ");
			again = in.next();
			
			if (again.equalsIgnoreCase("no")) {
				return;
			}
			
			user = determineUser();
			
			if (user == "customer") {
				Customer some = new Customer(menuManager);
				some.order();
			}
			else if (user == "staff") {
				Staff other = new Staff(menuManager);
				other.prompt();
			}
		} while (user != null);
		
	}
	
	private static String determineUser() {
		Scanner in = new Scanner(System.in);
		String user = null;
		int choice = 0;
		boolean valid = false;
	
		displayUserOptions();
		try {
			choice = in.nextInt();
		} catch (Exception e) {
			System.out.println("An error occurred due to an invalid choice. You will proceed as a customer.");
			choice = 1;
		}
		
		while (choice != 3 && !valid) {
			if (choice == 1) {
				valid = true;
				user = "customer";
			}
			else if (choice == 2) {
				valid = true;
				user = "staff";
			}
			else {
				System.out.println();
				System.out.println("---INVALID CHOICE---");
				System.out.println();
				displayUserOptions();
				try {
					choice = in.nextInt();
				} catch (Exception e) {
					System.out.println("An error occurred due to an invalid choice. You will proceed as a customer.");
					choice = 1;
				}
			}
		}
		return user;
	}
	
	private static void displayUserOptions() {
		System.out.println();
		System.out.println("Choose which user you are (Enter the corresponding integer)");
		System.out.println("1 - Customer");
		System.out.println("2 - Staff");
		System.out.println("3 - Terminate application");
		System.out.println();
		System.out.print("Your choice: ");
	}
	
}
