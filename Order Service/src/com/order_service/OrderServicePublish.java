package com.order_service;

import java.util.List;

public interface OrderServicePublish {
	void placeOrder(Order order);
    void cancelOrder(int orderId);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
}
