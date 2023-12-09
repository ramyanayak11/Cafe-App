/*
 * Name: RAMYA NAYAK
 * 
 * Course: CS 151
 * Final Project
 * Date: 12/08/2023
 * 
 */

import java.util.Scanner;

public class Staff {
	
	private Process MenuManager;
	Scanner in = new Scanner(System.in);

	Staff(Process menuManager) {
		MenuManager = menuManager;
	}
	
	protected void prompt() {
		boolean valid = false;
		int choice;
		System.out.println();
		System.out.println("Hello, staff! What would you like to do? (Enter the corresponding integer)");
		displayTaskOptions();
		try {
			choice = in.nextInt();
		} catch (Exception e) {
			System.out.println("An error occurred due to an invalid choice. You will be logged out of the staff profile.");
			choice = 4;
		}
		
		while (choice != 4) {
			if (choice == 1) {
				addItem();
			}
			else if (choice == 2) {
				editItem();
			}
			else if (choice == 3) {
				removeItem();
			}
			else {
				System.out.println();
				System.out.println("---INVALID CHOICE---");
				System.out.println();
				System.out.println("What would you like to do? (Enter the corresponding integer)");
				displayTaskOptions();
				choice = in.nextInt();
			}
			
			System.out.println();
			System.out.println("What would you like to do next? (Enter the corresponding integer)"); 
			displayTaskOptions();
			try {
				choice = in.nextInt();
			} catch (Exception e) {
				System.out.println("An error occurred due to an invalid choice. You will be logged out of the staff profile.");
				choice = 4;
			}
		}
	}
	
	private void addItem() {
		Scanner lineInput = new Scanner(System.in);
		String category = "";
		int choice;
		boolean valid = false;
		String name;
		double price;
		
		System.out.println();
		System.out.print("Enter the name of the new item: ");
		name = lineInput.nextLine();
		name = name.trim();
		
		System.out.print("Enter the price of this new item: ");
		try {
			price = in.nextDouble();
		} catch (Exception e) {
			System.out.println("An error occurred due to an invalid price entry. The price for this menu item will be set to $4.00.");
			price = 4.00;
		}

		categoryOptions();
		try {
			choice = in.nextInt();
		} catch (Exception e) {
			System.out.println("An error occurred due to an invalid category entry. This menu item will be placed in the food category.");
			choice = 2;
		}
		
		while (!valid) {
			if (choice == 1) {
				valid = true;
				category = "drink";
			}
			else if (choice == 2) {
				valid = true;
				category = "food";
			}
			else {
				System.out.println();
				System.out.println("---INVALID CHOICE---");
				System.out.println();
				categoryOptions();
				try {
					choice = in.nextInt();
				} catch (Exception e) {
					System.out.println("An error occurred due to an invalid category entry. This menu item will be placed in the food category.");
					choice = 2;
				}
			}
		}
		
		MenuManager.addItem(name, Math.round(price * 100) / 100d, category);
		System.out.println();
		System.out.println("NEW MENU:");
		MenuManager.showMenu();
	}
	
	private void removeItem() {
		Scanner lineInput = new Scanner(System.in);
		System.out.print("Enter the name of the item to be removed from the menu: ");
		String name = lineInput.nextLine();
		MenuManager.removeItem(name);
		
		System.out.println();
		System.out.println("NEW MENU:");
		MenuManager.showMenu();
	}
	
	private void editItem() {
		Scanner lineInput1 = new Scanner(System.in);
		Scanner lineInput2 = new Scanner(System.in);
		String originalName;
		String newName = null;
		double newPrice = -1;
		int option;
		
		MenuManager.showMenu();
		
		System.out.println("Please enter the name of the item you'd like to edit: ");
		originalName = lineInput1.nextLine();
		originalName = originalName.trim();
		
		editOptions();
		try {
			option = in.nextInt();
		} catch (Exception e) {
			System.out.println("An error occurred due to an invalid entry. You will be forwarded to editing this menu item's name.");
			option = 1;
		}
		
		boolean valid = false;
		
		while (!valid) {
			if (option == 1) {
				valid = true;
				System.out.println();
				System.out.print("What would you like to rename '" + originalName.trim() + "' to? ");
				newName = lineInput2.nextLine();
				MenuManager.editItem(originalName, newName.trim(), newPrice);
			}
			else if (option == 2) {
				valid = true;
				System.out.println();
				System.out.print("What is the new price of '" + originalName.trim() + "'? ");
				newPrice = in.nextDouble();
				MenuManager.editItem(originalName, newName, Math.round(newPrice * 100) / 100d);
			}
			else {
				System.out.println();
				System.out.println("---INVALID CHOICE---");
				System.out.println();
				editOptions();
				try {
					option = in.nextInt();
				} catch (Exception e) {
					System.out.println("An error occurred due to an invalid entry. You will be forwarded to editing this menu item's name.");
					option = 1;
				}
			}
		}
		System.out.println();
		System.out.println("NEW MENU:");
		MenuManager.showMenu();
	}
	
	private void displayTaskOptions() {
		System.out.println("1 - Add a new menu item");
		System.out.println("2 - Edit an existing menu item");
		System.out.println("3 - Remove an existing menu item");
		System.out.println("4 - Log out of the staff profile");
		System.out.println();
		System.out.print("Your choice: ");
	}
	
	private void categoryOptions() {
		System.out.println("Choose the category of this new item: (Enter the corresponding integer)");
		System.out.println("1 - Drink");
		System.out.println("2 - Food");
		System.out.println();
		System.out.print("Your choice: ");
	}
	
	private void editOptions() {
		System.out.println("Which of the following properties would you like to edit? (Enter the corresponding integer)");
		System.out.println("1 - Item name");
		System.out.println("2 - Item price");
	}

}
