package com.order_service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServicePublishImpl implements OrderServicePublish {
	  private Map<Integer, Order> orders = new HashMap<>();
	    private int nextOrderId = 1;
	 @Override
	    public void placeOrder(Order order) {
	        order.setOrderId(nextOrderId++);
	        orders.put(order.getOrderId(), order);
//	        System.out.println("Order placed: " + order);
	    }

	  

	    @Override
	    public void cancelOrder(int orderId) {
	        Order order = orders.get(orderId);
	        if (order != null) {
	            order.setStatus("Canceled");
	            System.out.println("Order canceled with ID: " + orderId);
	        } else {
	            System.out.println("Order not found with ID: " + orderId);
	        }
	    }

	    @Override
	    public Order getOrderById(int orderId) {
	        return orders.get(orderId);
	    }

	    @Override
	    public List<Order> getAllOrders() {
	        return new ArrayList<>(orders.values());
	    }
}
