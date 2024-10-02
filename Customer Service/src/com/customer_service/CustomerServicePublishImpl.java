package com.customer_service;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerServicePublishImpl implements CustomerServicePublish{
	
	ArrayList<Customer> customer = new ArrayList<>();
	
	public CustomerServicePublishImpl() {
		
        customer = new ArrayList<>();
        System.out.println("Before adding customers - Free Memory: " + Runtime.getRuntime().freeMemory());
        
        

        System.out.println("Before adding customers - Free Memory: " + Runtime.getRuntime().freeMemory());

		for (int i =0 ; i < customer.size(); i++) {
			String customerID = "ID_" + (i + 1);
            String customerName = "Name_" + (i + 1);
            String customerAddress = "customer_address_" + (i + 1);
            String customerMobile = "customer_address__mobile_" + (i + 1);
            String customerPaymentMethod = "customer_address__paymentMethod_" + (i + 1);
            
            Customer customerNew = new Customer(customerID, customerName, customerAddress, customerMobile, customerPaymentMethod);
            
            customer.add(customerNew);
		}
	}
	
	@Override
	public String publishService() {
		return "Welcome to the Customer Profile";
	}

	@Override
	public String getInput() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + "!");
        scanner.close();
        return name;
	}

	@Override
	public void addCustomer(Runnable callback) {
	    Scanner input = new Scanner(System.in);

	    Customer newCustomer = Customer.addCustomersFromConsole();
	    customer.add(newCustomer);

	    // Invoke the callback function after adding a customer
	    callback.run();
	}
	
	@Override
	public boolean isValidCustomer(String customerName) {
	    for (Customer customer : customer) {
	        if (customer.getCustomerName().equals(customerName)) {
	            return true;
	        }
	    }
	    return false;
	}

	@Override
	public void displayCustomerList() {
	    if (customer.isEmpty()) {
	        System.out.println("No registered customers currently.");
	    } else {
	        System.out.println("=============================================");
	        System.out.printf("| %-15s | %-25s | %-25s | %-15s | %-15s |%n", "Customer Id", "Customer Name", "Customer Address", "Mobile", "Payment Method");
	        System.out.println("|---------------|-------------------------|-------------------------|-----------------|-----------------|");

	        for (Customer customers : customer) {
	            System.out.printf("| %-15s | %-25s | %-25s | %-15s | %-15s |%n",
	                    customers.getCustomerID(), customers.getCustomerName(),
	                    customers.getCustomerAddress(), customers.getCustomerMobile(),
	                    customers.getCustomerPaymentMethod());
	        }

	        System.out.println("=============================================");
	    }
	}


	@Override
	public void displayCustomerProfile(String customerID) {
	    boolean isCustomerFound = false;

	    for (Customer customer : customer) {
	        if (customer.getCustomerID().equals(customerID)) {
	            System.out.println("=====================================");
	            System.out.println("Customer Profile for ID: " + customerID);
	            System.out.println("=====================================");
	            System.out.printf("%-20s%-20s%n", "Customer ID:", customer.getCustomerID());
	            System.out.printf("%-20s%-20s%n", "Customer Name:", customer.getCustomerName());
	            System.out.printf("%-20s%-20s%n", "Customer Address:", customer.getCustomerAddress());
	            System.out.printf("%-20s%-20s%n", "Mobile:", customer.getCustomerMobile());
	            System.out.printf("%-20s%-20s%n", "Payment Method:", customer.getCustomerPaymentMethod());
	            System.out.println("=====================================");

	            isCustomerFound = true;
	            break;
	        }
	    }

	    if (!isCustomerFound) {
	        System.out.println("*********************************************");
	        System.out.println("Customer with ID " + customerID + " was not found.");
	        System.out.println("*********************************************");
	    }
	}

//
//	@Override
//	public void displayCustomerAddressList() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void displayPaymentMethod() {
//		// TODO Auto-generated method stub
//		
//	}
//
	@Override
	public void updateCustomerProfile() {
	    Scanner input = new Scanner(System.in);
	    System.out.print("Enter the Customer ID to update: ");
	    String cusID = input.nextLine();

	    boolean isCustomerFound = false;
	    for (Customer item : customer) {
	        if (item.getCustomerID().equals(cusID)) {
	            // Prompt the user to enter the new values for the item
	            System.out.println("Enter the new details for Customer with ID " + cusID + ":");

	            // Update Name
	            System.out.print("New Name (press Enter to skip): ");
	            String newName = input.nextLine().trim();

	            // Update Address
	            System.out.print("New Address (press Enter to skip): ");
	            String newAddress = input.nextLine().trim();

	            // Update Mobile number with number validation
	            String newMobile;
	            do {
	                System.out.print("New Mobile number (press Enter to skip, numeric only): ");
	                newMobile = input.nextLine().trim();

	                if (!newMobile.isEmpty() && !newMobile.matches("\\d+")) {
	                    System.out.println("Invalid contact number. Please enter numeric characters only.");
	                }
	            } while (!newMobile.isEmpty() && !newMobile.matches("\\d+"));

	            // Update the item with the new values (if provided)
	            if (!newName.isEmpty()) {
	                item.setCustomerName(newName);
	            }

	            if (!newAddress.isEmpty()) {
	                item.setCustomerAddress(newAddress);
	            }

	            if (!newMobile.isEmpty()) {
	                item.setCustomerMobile(newMobile);
	            }

	            System.out.println("Customer with ID " + cusID + " has been updated.");
	            displayCustomerProfile(cusID);

	            isCustomerFound = true;
	            break;
	        }
	    }

	    if (!isCustomerFound) {
	        System.out.println("Item with ID " + cusID + " was not found in the list.");
	    }
	}


	// In CustomerServicePublishImpl class

	@Override
	public void deleteCustomer(Runnable callback) {
	    Scanner input = new Scanner(System.in);
	    System.out.print("Enter the Customer ID to delete: ");
	    String cusID = input.nextLine();

	    boolean isCustomerFound = false;
	    for (Customer item : customer) {
	        if (item.getCustomerID().equals(cusID)) {
	            customer.remove(item);
	            isCustomerFound = true;
	            break;
	        }
	    }

	    if (isCustomerFound) {
	        System.out.println("Customer with ID " + cusID + " has been deleted.");
	        this.displayCustomerList();

	        // Invoke the callback function after successful deletion
	        callback.run();
	    } else {
	        System.out.println("Customer with ID " + cusID + " was not found in the list.");
	    }
	}

	
	@Override
	public void searchCustomer() {
	    Scanner input = new Scanner(System.in);
	    System.out.print("Enter the Customer ID to Search: ");
	    String cusID = input.nextLine();

	    boolean isCustomerFound = false;
	    for (Customer item : customer) {
	        if (item.getCustomerID().equals(cusID)) {
	            System.out.println("================================");
	            System.out.println("  Customer Profile for ID: " + cusID);
	            System.out.println("================================");
	            System.out.printf("| %-18s | %-30s |%n", "Customer ID", item.getCustomerID());
	            System.out.printf("| %-18s | %-30s |%n", "Customer Name", item.getCustomerName());
	            System.out.printf("| %-18s | %-30s |%n", "Customer Address", item.getCustomerAddress());
	            System.out.printf("| %-18s | %-30s |%n", "Mobile", item.getCustomerMobile());
	            System.out.printf("| %-18s | %-30s |%n", "Payment Method", item.getCustomerPaymentMethod());
	            System.out.println("================================");

	            isCustomerFound = true;
	            break;
	        }
	    }

	    if (!isCustomerFound) {
	        System.out.println("Customer with ID " + cusID + " was not found.");
	    }
	}


}
