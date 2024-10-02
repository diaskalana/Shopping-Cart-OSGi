package com.shoppingcartservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServicePublishImpl implements CartServicePublish {
	private Map<Integer, CartItem> cartItems = new HashMap<>();
	private int nextItemId = 1;

	@Override
	public void addItem(CartItem item) {
		item.setItemId(nextItemId++);
		cartItems.put(item.getItemId(), item);
		triggerCartUpdate(("Item(s) added: " + item.getItemName() + " with the quantity of " + item.getQuantity()), 0);
	}

	@Override
	public void removeItem(int itemId) {
		CartItem item = cartItems.get(itemId);
		if (item != null) {
			cartItems.remove(itemId);
			triggerCartUpdate(
					("Item(s) removed: " + item.getItemName() + " with the quantity of " + item.getQuantity()), 0);
		} else {
			triggerCartUpdate(("Item(s) not found with ID: " + itemId), 1);
		}
	}

	@Override
	public List<CartItem> getCartItems() {
		return new ArrayList<>(cartItems.values());
	}

	@Override
	public void triggerCartUpdate(String message, int error) {
		if (error == 1) {
			System.out.println("Cart Error -> " + message + '!');
		} else {
			System.out.println("Cart Update Triggered -> " + message);
		}
	}
}
