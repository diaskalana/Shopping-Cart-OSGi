package com.customer_service;

import java.util.Scanner;

public class Customer {
	
	private String customerID;
	private String customerName;
	private String customerAddress;
	private String customerMobile;
	private String customerPaymentMethod;
    private static int customerCount = 3;
	
	
	public Customer(String customerID, String customerName, String customerAddress, String customerMobile,
			String customerPaymentMethod) {
		
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerMobile = customerMobile;
		this.customerPaymentMethod = customerPaymentMethod;
	}


	public String getCustomerID() {
		return customerID;
	}


	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerAddress() {
		return customerAddress;
	}


	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}


	public String getCustomerMobile() {
		return customerMobile;
	}


	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}


	public String getCustomerPaymentMethod() {
		return customerPaymentMethod;
	}


	public void setCustomerPaymentMethod(String customerPaymentMethod) {
		this.customerPaymentMethod = customerPaymentMethod;
	}
	
	public static Customer addCustomersFromConsole() {
	    Scanner input = new Scanner(System.in);

	    String customerID = Integer.toString(++customerCount);

	    // Ensure customer name is required
	    String customerName;
	    do {
	        System.out.print("Enter Customer Name: ");
	        customerName = input.nextLine().trim();
	        if (customerName.isEmpty()) {
	            System.out.println("Customer Name is required. Please try again.");
	        }
	    } while (customerName.isEmpty());

	    // Ensure customer address is required
	    String customerAddress;
	    do {
	        System.out.print("Enter Customer Address: ");
	        customerAddress = input.nextLine().trim();
	        if (customerAddress.isEmpty()) {
	            System.out.println("Customer Address is required. Please try again.");
	        }
	    } while (customerAddress.isEmpty());

	    // Ensure contact number is required and numeric
	    String customerMobile;
	    do {
	        System.out.print("Enter Customer Contact Number (numeric only): ");
	        customerMobile = input.nextLine().trim();
	        if (customerMobile.isEmpty() || !customerMobile.matches("\\d+")) {
	            System.out.println("Invalid contact number. Please enter numeric characters only.");
	        }
	    } while (customerMobile.isEmpty() || !customerMobile.matches("\\d+"));

	    // Validate the payment method
	    String customerPaymentMethod;
	    do {
	        System.out.print("Enter Customer Payment Method (Card Payment, Cash on Delivery): ");
	        customerPaymentMethod = input.nextLine().toLowerCase(); // Convert to lowercase for case-insensitive comparison

	        if (!customerPaymentMethod.equals("card payment") && !customerPaymentMethod.equals("cp") &&
	                !customerPaymentMethod.equals("cash on delivery") && !customerPaymentMethod.equals("cod")) {
	            System.out.println("Invalid payment method. Please enter 'Card Payment' (or 'CP'), 'Cash on Delivery' (or 'COD').");
	        }
	    } while (!customerPaymentMethod.equals("card payment") && !customerPaymentMethod.equals("cp") &&
	            !customerPaymentMethod.equals("cash on delivery") && !customerPaymentMethod.equals("cod"));

	    System.out.println("\nCustomer Information Summary:");
	    System.out.println("=============================");
	    System.out.printf("%-20s: %s%n", "Customer ID", customerID);
	    System.out.printf("%-20s: %s%n", "Customer Name", customerName);
	    System.out.printf("%-20s: %s%n", "Customer Address", customerAddress);
	    System.out.printf("%-20s: %s%n", "Contact Number", customerMobile);
	    System.out.printf("%-20s: %s%n", "Payment Method", customerPaymentMethod);
	    System.out.println("=============================");

	    Customer newCustomer = new Customer(customerID, customerName, customerAddress, customerMobile, customerPaymentMethod);

	    return newCustomer;
	}

	
	
}
