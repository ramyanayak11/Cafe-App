/*
 * Name: RAMYA NAYAK
 * Date: December 08, 2023
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Process {
	protected static ArrayList<MenuItem> menu;
	
	Process() {
		menu = new ArrayList();
		
		// basic menu created upon initialization
		menu.add(new MenuItem("Hot Americano", 4.00, "drink"));
		menu.add(new MenuItem("Iced Americano", 4.00, "drink"));
		menu.add(new MenuItem("Hot Latte", 4.00, "drink"));
		menu.add(new MenuItem("Iced Latte", 4.00, "drink"));
		menu.add(new MenuItem("Cappucino", 4.50, "drink"));
		menu.add(new MenuItem("Chai Latte", 5.00, "drink"));
		menu.add(new MenuItem("Banana Bread", 4.00, "food"));
		menu.add(new MenuItem("Sandwich", 5.00, "food"));
	}

	// Shows menu to user
	protected void showMenu() {
		int printIndex = 1;
		System.out.println();
		System.out.println("----------------------MENU----------------------");
		System.out.println();
		System.out.println("Drinks:");
		for (int i = 0; i < menu.size(); ++i) {
			if (menu.get(i).category == "drink") {
				System.out.println(printIndex + ") " + menu.get(i).name + " --- $" + menu.get(i).price);
				++printIndex;
			}
		}
		printIndex = 1;
		System.out.println();
		System.out.println("Food:");
		for (int i = 0; i < menu.size(); ++i) {
			if (menu.get(i).category == "food") {
				System.out.println(printIndex + ") " + menu.get(i).name + " --- $" + menu.get(i).price);
				++printIndex;
			}
		}
		System.out.println();
		System.out.println("------------------------------------------------");
	}

	// Shows users' order summary
	protected void showOrderSummary(ArrayList<MenuItem> order, double total) {
		double tax = Math.round((total*0.1025) * 100) / 100d;
		
		System.out.println();
		System.out.println("Your order summary today is: ");
		System.out.printf("\n\n\t%2s\t%-30s\t\t%15s\n","QTY","Description", "Price");
		
		for (int i = 0; i < order.size(); ++i) {
			System.out.printf("\t%2d\t%-30s\t\t%15.2f\n",1,order.get(i).name,order.get(i).modifiedPrice);
		}
		System.out.printf("\n\t%33s\t\t%15.2f\n","SUBTOTAL",total);
		System.out.printf("\t%33s\t\t%15.2f\n","TAX",tax);
		System.out.printf("\t%33s\t\t%15.2f\n","TOTAL",total+tax);
		System.out.println();
	}

	// Adds item to menu
	protected void addItem(String name, double price, String category) {
		menu.add(new MenuItem(name, price, category));
	}

	// Removes item from menu
	protected void removeItem(String name) {
		int index = menuContains(name);
		
		if (index != -1) {
			menu.remove(index);
		}
	}

	// Edits menu item
	protected void editItem(String prevName, String newName, double price) {
		int index = menuContains(prevName);
		
		if (index == -1) {
			return;
		}
		
		if (newName != null && price == -1) {
			menu.get(index).name = newName;
		}
		else if (newName == null && price != -1) {
			menu.get(index).price = price;
		}
	}

	// Checks whether the menu contains given item
	protected int menuContains(String name) {
		for (int i = 0; i < menu.size(); ++i) {
			if (menu.get(i).name.compareToIgnoreCase(name) == 0) {
				return i;
			}
		}
		return -1;
	}

	// Resets menu item's price to original
	protected void resetModifiedPrice() {
		for (int i = 0; i < menu.size(); ++i) {
			menu.get(i).modifiedPrice = menu.get(i).price;
		}
	}
	
	// inner class MenuItem
	class MenuItem {
		protected String name;
		protected double price;
		protected String category;
		protected double modifiedPrice;
		
		MenuItem() {}
		
		MenuItem(String name, double price, String category) {
			this.name = name;
			this.price = price;
			this.category = category;
			modifiedPrice = this.price;
		}
	}
	
}
