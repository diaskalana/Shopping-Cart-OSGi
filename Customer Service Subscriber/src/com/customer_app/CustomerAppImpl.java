package com.customer_app;

import java.util.Scanner;
import com.customer_service.CustomerServicePublish;

public class CustomerAppImpl implements CustomerApp {
	
	CustomerServicePublish customerService;
	
	public CustomerAppImpl(CustomerServicePublish customerServicePublish) {
		this.customerService = customerServicePublish;
	}

	@Override
	public void start() {
		interactWithUser();
		
	}

	private void interactWithUser() {
	    Scanner input = new Scanner(System.in);
	    int userChoice;

	    while (true) {
	        System.out.println("=======================================");
	        System.out.println("        Welcome to Aparell Shopping        ");
	        System.out.println("=======================================");
	        System.out.println("1. Login");
	        System.out.println("2. Sign Up");
	        System.out.println("99: Exit");
	        System.out.print("Enter your choice: ");

	        userChoice = input.nextInt();
	        clearConsole(); // Clear console for a cleaner appearance

	        switch (userChoice) {
	            case 1:
	                // Implement login functionality
	                System.out.println("=============== Customer Login ===============");
	                System.out.print("Enter Your Username: ");
	                String customerName = input.next();
	                System.out.println("===============================================");

	                if (customerName.equalsIgnoreCase("Admin@123")) {
	                    // Admin login
	                    System.out.println("Admin Login Successful!");
	                    adminMainMenu();
	                } else if (customerService.isValidCustomer(customerName)) {
	                    // Customer login
	                    System.out.println("=================================");
	                    System.out.println("           Login Successful          ");
	                    System.out.println("=================================");
	                    customerMainMenu();
	                } else {
	                    System.out.println("Invalid Username. Please try again.");
	                }
	                break;
	            case 2:
	                // Sign Up
	                System.out.println("================= Sign Up ===================");
	                customerService.addCustomer(this::interactWithUser);
	                break;
	            case 99:
	                System.out.println("Exiting the Aparell Shopping. Goodbye!");
	                return;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	}


	// Helper method to clear console
	private void clearConsole() {
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
	}

	private void adminMainMenu() {
	    // Display admin menu options
	    int adminChoice;
	    while (true) {
	        Scanner input = new Scanner(System.in);
	        System.out.println("============================");
	        System.out.println("         Admin Menu          ");
	        System.out.println("============================");
	        System.out.println("1. View Customer List");
	        System.out.println("2. Delete Profile");
	        System.out.println("3. Search Customers");
	        System.out.println("99: Logout");
	        System.out.print("Enter your choice: ");

	        adminChoice = input.nextInt();

	        switch (adminChoice) {
	            case 1:
	                customerService.displayCustomerList();
	                break;
	            case 2:
	                // Pass a callback to deleteCustomer
	                customerService.deleteCustomer(() -> {
	                    // Callback function to return to adminMainMenu
	                    adminMainMenu();
	                });
	                break;
	            case 3:
	                customerService.searchCustomer();
	                break;
	            case 99:
	                System.out.println("Logging out.");
	                return;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	}
	
	private void customerMainMenu() {
	    // Display customer menu options
	    int customerChoice;
	    while (true) {
	        Scanner input = new Scanner(System.in);
	        System.out.println("============================");
	        System.out.println("       Customer Menu         ");
	        System.out.println("============================");
	        System.out.println("1. Update Profile");
	        System.out.println("2. Delete Profile");
	        System.out.println("3. View Profile Customer");
	        System.out.println("99: Logout");
	        System.out.print("Enter your choice: ");

	        customerChoice = input.nextInt();

	        switch (customerChoice) {
	            case 1:
	                customerService.updateCustomerProfile();
	                break;
	            case 2:
	                // Pass a callback to deleteCustomer
	                customerService.deleteCustomer(() -> {
	                    // Callback function to return to interactWithUser
	                    interactWithUser();
	                });
	                return; // Exit the customerMainMenu loop
	            case 3:
	                // Assuming 6 is the option for viewing a customer profile
	                System.out.print("Enter the Customer ID: ");
	                String customerID = input.next();
	                customerService.displayCustomerProfile(customerID);
	                break;
	            case 99:
	                System.out.println("Logging out.");
	                return;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	}

}
