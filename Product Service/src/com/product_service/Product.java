package com.product_service;

public class Product {
	private static int count = 0;
	private String Id;
	private String name;
	private String brand;
	private String model;
	private int qty;
	private double price;
	private char currency = '$';
	private boolean featured;
	
	public Product(String name, String brand, String model, int qty, double price, boolean featured) {
		count++;
		this.Id = "ITM000"+count;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.qty = qty;
		this.price = price;
		this.featured = featured;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public char getCurrency() {
		return currency;
	}

	public void setCurrency(char currency) {
		this.currency = currency;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}
}
