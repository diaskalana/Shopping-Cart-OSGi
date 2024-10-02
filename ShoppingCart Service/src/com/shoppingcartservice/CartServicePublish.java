package com.shoppingcartservice;

import java.util.List;

public interface CartServicePublish {
	void addItem(CartItem item);

	void removeItem(int itemId);

	List<CartItem> getCartItems();

	void triggerCartUpdate(String message, int error);
}
