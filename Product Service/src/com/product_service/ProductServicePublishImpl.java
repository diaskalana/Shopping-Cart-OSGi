package com.product_service;

import java.util.ArrayList;

public class ProductServicePublishImpl implements ProductServicePublish {

	// Items
	String[] names = {"Laptop", "Laptop", "Laptop", "Smartphone", "Smartphone", "Smartphone", "Smartphone", "Headphones", "Headphones", "Smartwatch", "Smartwatch", "Smartwatch", "Smartwatch", "Wireless Mouse", "External Hard Drive", "External Hard Drive", "External Hard Drive", "External Hard Drive", "Portable Charger", "Portable Charger"};
	String[] brands = {"Apple", "Dell", "Asus", "Samsung", "Samsung", "Google", "Apple", "Sony", "JBL", "Google", "Apple", "Samsung", "Fitbit", "Logitech", "Seagate", "Seagate", "Seagate", "Western Digital", "Anker", "Aspor"};
	String[] models = {"MacBook Pro", "Inspiron 15 5000", "Rog Strix 16", "Galaxy S21", "Galaxy S23 Ultra", "Pixel 7", "Iphone 14 Pro", "WH-1000XM4", "Tune 510BT", "Pixel Watch 2", "Series 9", "Galaxy Fit3", "Sense 2", "MX Master 3", "512GB", "1TB", "2TB", "1TB", "10000mAh", "A396 20000mAh"};
	int[] quantities = {10, 15, 5, 8, 3, 5, 10, 55, 25, 28, 20, 15, 35, 67, 50, 50, 50, 30, 30, 28};
	double[] prices = {1499, 1899, 1999, 499, 799, 799, 899, 349, 49.95, 349.99, 59.99, 69.99, 249.95, 99, 39, 59, 89, 55, 29, 39};
	boolean[] featured = {false, true, true, false, true, false, true, false, false, true, false, true, false, false, false, false, true, false, false, false};

	//Getting the minimum number that can form a full product
	int numProducts = Math.min(names.length, Math.min(brands.length, Math.min(models.length,Math.min(quantities.length, Math.min(prices.length, featured.length)))));

	ArrayList<Product> productList = new ArrayList<Product>();
	
	public ProductServicePublishImpl() {
		for (int i = 0; i < numProducts; i++) {
		    Product product = new Product(names[i], brands[i], models[i], quantities[i], prices[i], featured[i]);
		    productList.add(product);
		}
	}
	
	@Override
	public Product getProductDetails(String productId) {
		for (Product item : productList) {
			if(item.getId().equals(productId)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Product> searchProducts(String filter) {
		ArrayList<Product> filteredProducts = new ArrayList<Product>();
		for (Product item : productList) {
			if(item.getName().contains(filter) || item.getBrand().contains(filter) || item.getModel().contains(filter)) {
				filteredProducts.add(item);
			}
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> searchProductsByName(String filter) {
		ArrayList<Product> filteredProducts = new ArrayList<Product>();
		for (Product item : productList) {
			if(item.getName().contains(filter)) {
				filteredProducts.add(item);
			}
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> searchProductsByBrand(String filter) {
		ArrayList<Product> filteredProducts = new ArrayList<Product>();
		for (Product item : productList) {
			if(item.getBrand().contains(filter)) {
				filteredProducts.add(item);
			}
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> searchProductsByModel(String filter) {
		ArrayList<Product> filteredProducts = new ArrayList<Product>();
		for (Product item : productList) {
			if(item.getModel().contains(filter)) {
				filteredProducts.add(item);
			}
		}
		return filteredProducts;
	}

	@Override
	public int getProductQuantity(String productId) {
		for (Product item : productList) {
			if(item.getModel().equals(productId)) {
				return item.getQty();			}
		}
		return 0;
	}

	@Override
	public ArrayList<Product> getFeaturedProducts() {
		ArrayList<Product> featuredProducts = new ArrayList<Product>();
		for (Product item : productList) {
			if(item.isFeatured()) {
				featuredProducts.add(item);
			}
		}
		return featuredProducts;
	}

	@Override
	public ArrayList<Product> getAllProducts() {
		return productList;
	}
	

	@Override
	public void addProduct(String name, String brand, String model, int qty, double price, boolean featured) {
		Product product = new Product(name, brand, model, qty, price, featured);
	    productList.add(product);
	}
	

	@Override
	public boolean removeProduct(String productId) {
		for (Product item : productList) {
			if(item.getId().equals(productId)) {
				productList.remove(item);
				return true;
			}
		}
		return false;
	}

}
