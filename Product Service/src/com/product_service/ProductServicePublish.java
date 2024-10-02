package com.product_service;

import java.util.ArrayList;

public interface ProductServicePublish {
	// 
	public Product getProductDetails(String productId);
	
	//
	public ArrayList<Product> searchProducts(String filter);
	
	//
	public ArrayList<Product> searchProductsByName(String filter);
	
	//
	public ArrayList<Product> searchProductsByBrand(String filter);
	
	//
	public ArrayList<Product> searchProductsByModel(String filter);
	
	//
	public ArrayList<Product> getAllProducts();
	
	//
	public ArrayList<Product> getFeaturedProducts();
	
	//
	public int getProductQuantity(String productId);
	
	public void addProduct(String name, String brand, String model, int qty, double price, boolean featured);
	
	public boolean removeProduct(String productId);
}
