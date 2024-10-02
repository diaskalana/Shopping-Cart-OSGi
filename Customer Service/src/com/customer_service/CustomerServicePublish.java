package com.customer_service;

public interface CustomerServicePublish {
	
	public String publishService();
	public String getInput();
//	public void addCustomer(); 
	public void displayCustomerList();
    public void displayCustomerProfile(String customerID);
//	public void displayCustomerAddressList();
//	public void displayPaymentMethod();	
	public void updateCustomerProfile();
//	public void deleteCustomer();
	public void searchCustomer();
    boolean isValidCustomer(String customerID);
	void deleteCustomer(Runnable callback);
	void addCustomer(Runnable callback); 

}
