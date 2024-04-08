/*
 * Name: RAMYA NAYAK
 * Date: December 8, 2023
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;


public class Customer {
	
	private Process MenuManager;
	private ArrayList<Process.MenuItem> order;
	private double orderTotal;
	Scanner in = new Scanner(System.in);
	
	Customer(Process menuManager) {
		MenuManager = menuManager;
		orderTotal = 0.00;
		order = new ArrayList();
	}

	// Takes order from user
	protected void order() {
		String input;
		int index;
		MenuManager.showMenu();
		
		System.out.println("Welcome!");
		System.out.println("Please enter the names of the items you'd like to order, separated by a comma: ");
		input = in.nextLine();
		
		String[] orderString = input.split(",");
		System.out.println();
		
		for (int i = 0; i < orderString.length; ++i) {
			index = MenuManager.menuContains(orderString[i].trim());
			
			if (index == -1) {
				System.out.println("Warning: " + orderString[i].trim() + " is not on the menu.");
			}
			else {
				order.add(MenuManager.menu.get(index));
			}
		}
		
		customization();
		paymentStage();
		MenuManager.resetModifiedPrice();
	}

	// Adds any customizations to drinks and adjusts the price accordingly
	protected void customization() {
		ArrayList<Integer> drinks = containsDrink(order, new ArrayList());
		
		if (drinks.size() == 0) {										// checks whether order contains a drink
			return;
		}
		
		String size;
		String whippedCream;
		
		for (int i = 0; i < drinks.size(); ++i) {
			System.out.println();
			System.out.println("Which size would you like '" + order.get(drinks.get(i)).name + "' in? (small +$0 / medium +$1 / large +$2)");
			System.out.printf("Please input one of the following (small/medium/large): ");
			size = in.next();
			if (size.compareToIgnoreCase("medium") == 0) {
				order.get(drinks.get(i)).modifiedPrice += 1.0;
			}
			else if (size.compareToIgnoreCase("large") == 0) {
				order.get(drinks.get(i)).modifiedPrice += 2.0;
			}
			else if (size.compareToIgnoreCase("small") != 0) {
				System.out.println();
				System.out.print("\tInvalid size entry. You will proceed with a size small for this drink.");
			}
			
			System.out.println();
			System.out.printf("Would you like whipped cream on top of '" + order.get(drinks.get(i)).name + "' for an additional $1? (yes/no) : ");
			whippedCream = in.next();
			if (whippedCream.compareToIgnoreCase("yes") == 0) {
				order.get(drinks.get(i)).modifiedPrice += 1.0;
			}
			else if (whippedCream.compareToIgnoreCase("no") != 0) {
				System.out.print("\tInvalid entry. You will proceed with no whipped cream for this drink.");
			}
		}
	}
	
	protected ArrayList<Integer> containsDrink(ArrayList<Process.MenuItem> order, ArrayList<Integer> drinks) {
		for (int i = 0; i < order.size(); ++i) {
			if (order.get(i).category == "drink") {
				drinks.add(i);
			}
		}
		return drinks;
	}

	// Shows the order summary, takes payment, and displays the change to be received
	protected void paymentStage() {
		calculateTotal();
		MenuManager.showOrderSummary(order, orderTotal);
		
		System.out.println();
		System.out.print("Please enter the amount you'd like to pay (only enter the number): ");
		double paid;
		try {
			paid = in.nextDouble();
		} catch (Exception e) {
			System.out.println("Invalid entry for payment. You have paid $0.00 for your order.");
			paid = 0.00;
		}
		
		double change = calculateChange(orderTotal, paid);
		
		System.out.println();
		System.out.println("The change you'll receive is $" + change);
		System.out.println();
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println("Thank you for stopping by. Have a great day!");
	}

	// Calculates total cost by iterating through ordered items
	protected void calculateTotal() {
		orderTotal = 0.00;
		
		for (int i = 0; i < order.size(); ++i) {
			orderTotal += order.get(i).modifiedPrice;
		}
		orderTotal = Math.round(orderTotal * 100) / 100d;
	}

	// Calculates the change and rounds accordingly
	protected double calculateChange(double total, double paid) {
		double change = paid - (total*1.1025);
		while (Math.round(change * 100) / 100d < 0) {
			Scanner input = new Scanner(System.in);
			System.out.println();
			System.out.println("Insufficient money was provided. Your total is $" + Math.round((total*1.1025) * 100) / 100d);
			System.out.print("\tPlease enter the amount you'd like to pay (only enter the number): ");
			
			try {
				paid = input.nextDouble();
			} catch (Exception e) {
				System.out.println("Invalid entry for payment. You have paid $0.00 for your order.");
				paid = 0.00;
			}
			change = paid - (total*1.1025);
		}
		return Math.round(change * 100) / 100d;
	}
	
}
