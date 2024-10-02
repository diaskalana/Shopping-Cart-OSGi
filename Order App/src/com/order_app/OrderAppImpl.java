package com.order_app;

import java.util.List;
import java.util.Scanner;

import com.order_service.Order;
import com.order_service.OrderServicePublish;

public class OrderAppImpl implements OrderApp{
	
	OrderServicePublish orderService;

	public OrderAppImpl(OrderServicePublish orderServicePublish) {
		this.orderService = orderServicePublish;
	}
	
	
	
	
	
	@Override
	public void start() {
		interactWithUser();
		
		
	}
	 private void interactWithUser() {
	        Scanner scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("\n=== Order Service Menu ===");
	            System.out.println("1. Place Order");
	            System.out.println("2. Cancel Order");
	            System.out.println("3. Show All Orders");
	            System.out.println("4. Get Order by ID");
	            System.out.println("5. Exit");

	            System.out.print("\nEnter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (choice) {
	                case 1:
	                	System.out.println("\n=== Place Order ===");
	                	System.out.print("Enter customer name: ");
	                	String customerName = scanner.nextLine();
	                	System.out.print("Enter total price: ");
	                	double totalPrice = scanner.nextDouble();
	                	scanner.nextLine(); 

	                	// Ask for payment confirmation
	                	System.out.print("Confirm payment (yes/no): ");
	                	String paymentConfirmation = scanner.nextLine().trim().toLowerCase();

	                	if (paymentConfirmation.equals("yes")) {
	                	    Order order = new Order(1, customerName, totalPrice, "Payment Confirmed");
	                	    orderService.placeOrder(order);
	                	    System.out.println("\nOrder placed successfully.");
	                	} else if (paymentConfirmation.equals("no")) {
	                	    System.out.println("\nOrder placement canceled.");
	                	} else {
	                	    System.out.println("\nInvalid input. Order placement canceled.");
	                	}

	                    break;
	                case 2:
	                    System.out.println("\n=== Cancel Order ===");
	                    System.out.print("Enter order ID to cancel: ");
	                    int orderIdToCancel = scanner.nextInt();
	                    orderService.cancelOrder(orderIdToCancel);
	                    break;
	                case 3:
	                    System.out.println("\n=== All Orders ===");
	                    List<Order> allOrders = orderService.getAllOrders();
	                    if (allOrders.isEmpty()) {
	                        System.out.println("No orders found.");
	                    } else {
	                        System.out.println(formatOrderList(allOrders));
	                    }
	                    break;

	                case 4:
	                    System.out.println("\n=== Get Order by ID ===");
	                    System.out.print("Enter order ID: ");
	                    int orderId = scanner.nextInt();
	                    scanner.nextLine(); 
	                    Order orderById = orderService.getOrderById(orderId);
	                    if (orderById != null) {
	                        System.out.println("\nOrder details:");
	                        System.out.println(formatOrder(orderById));
	                    } else {
	                        System.out.println("\nOrder not found with ID: " + orderId);
	                    }
	                    break;
	                case 5:
	                    System.out.println("\nExiting the Order Service application.");
	                    return;
	                default:
	                    System.out.println("\nInvalid choice. Please try again.");
	            }
	        }
	    }

	    private String formatOrderList(List<Order> orders) {
	        StringBuilder builder = new StringBuilder();
	        builder.append("+------------+-----------------+--------------+-------------------+\n");
	        builder.append(String.format("| %-10s | %-15s | %-12s | %-18s |\n",
	                "Order ID", "Customer Name", "Total Price", "Status"));
	        builder.append("+------------+-----------------+--------------+-------------------+\n");
	        
	        for (Order order : orders) {
	            builder.append(String.format("| %-10d | %-15s | $%-11.2f | %-18s |\n",
	                    order.getOrderId(), order.getCustomerName(), order.getTotalPrice(), order.getStatus()));
	        }
	        
	        builder.append("+------------+-----------------+--------------+-------------------+\n");
	        return builder.toString();
	    }



	    private String formatOrder(Order order) {
	        StringBuilder formattedOrder = new StringBuilder();
	        formattedOrder.append("+------------+-----------------+--------------+-------------------+\n");
	        formattedOrder.append(String.format("| %-10s | %-15s | %-12s | %-18s |\n",
	                "Order ID", "Customer Name", "Total Price", "Status"));
	        formattedOrder.append("+------------+-----------------+--------------+-------------------+\n");
	        formattedOrder.append(String.format("| %-10d | %-15s | $%-11.2f | %-18s |\n",
	                order.getOrderId(), order.getCustomerName(), order.getTotalPrice(), order.getStatus()));
	        formattedOrder.append("+------------+-----------------+--------------+-------------------+\n");
	        return formattedOrder.toString();
	    }


}
