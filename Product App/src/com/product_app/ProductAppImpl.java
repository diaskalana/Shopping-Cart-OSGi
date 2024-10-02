package com.product_app;

import java.util.ArrayList;
import java.util.Scanner;

import com.product_service.Product;
import com.product_service.ProductServicePublish;
import com.shoppingcartapp.CartApp;

public class ProductAppImpl implements ProductApp {

	private ProductServicePublish productService;
	private CartApp cartApp;
	
	private final String ADMIN_PWD = "Admin@123";

	public ProductAppImpl(ProductServicePublish productService, CartApp cartApp) {
		this.productService = productService;
		this.cartApp = cartApp;
	}
	
	@Override
	public void start() {
		interactWithUser();		
	}
	
	private void displayPoducts(ArrayList<Product> productList, String name) {    	
    	System.out.printf("%40s%s%-40s\n", "", name, "");
    	System.out.println("=================================================================================================");
    	System.out.println("| Product Id  |         Type          |       Brand      |        Model       |      Price      |");
    	System.out.println("=================================================================================================");
    	for (Product product : productList) {
    	    System.out.printf("|  %-10s |  %-20s |  %-15s |  %-17s |     %s %-9s |\n", product.getId(), product.getName(), product.getBrand(), product.getModel(), product.getCurrency(), product.getPrice());
    	}
    	System.out.println("=================================================================================================");
    }

    private void interactWithUser() {
        Scanner scanner = new Scanner(System.in);
        String pwd;
        ArrayList<Product> productList = new ArrayList<Product>();
        while (true) {
            System.out.println("\n=== Product Service Menu ===");
            System.out.println("1. Show All Products");
            System.out.println("2. Show Featured Products");
            System.out.println("3. Filter Products");
            System.out.println("4. Add To Cart");
            System.out.println("");
            System.out.println("*** ADMIN ONLY ***");
            System.out.println("5. Add Products");
            System.out.println("6. Remove Products");
            System.out.println("");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	productList = productService.getAllProducts();
                	displayPoducts(productList, "Products");

                    break;
                case 2:
                	productList = productService.getFeaturedProducts();
                	displayPoducts(productList, "Featured Products");
                	
                    break;
                case 3:
                	boolean x = true;
                	while (x) {
	                    System.out.println("\n=== Filter Menu ===");
	                    System.out.println("1. Filter All");
	                    System.out.println("2. Filter By Type");
	                    System.out.println("3. Filter By Brand");
	                    System.out.println("4. Filter By Model");
	                    System.out.println("5. Back");

	                    System.out.print("\nEnter your choice: ");
	                    choice = scanner.nextInt();
	                    
	                    switch (choice) {
	                    	case 1:
	                    		System.out.print("\nEnter Filter: ");
	                    		String filter = scanner.next();
	                    		productList = productService.searchProducts(filter);
	                        	displayPoducts(productList, "Products");
	                        	x = false;
	                        	
	                        	break;
	                    	case 2:
	                    		System.out.print("\nEnter Type: ");
	                    		String type = scanner.next();
	                    		productList = productService.searchProductsByName(type);
	                        	displayPoducts(productList, "Products");
	                        	x = false;
	                        	
	                        	break;
	                    	case 3: 
	                    		System.out.print("\nEnter Brand: ");
	                    		String brand = scanner.next();
	                    		productList = productService.searchProductsByBrand(brand);
	                        	displayPoducts(productList, "Products");
	                        	x = false;
	                        	
	                        	break;
	                    	case 4: 
	                    		System.out.print("\nEnter Brand: ");
	                    		String Model = scanner.next();
	                    		productList = productService.searchProductsByBrand(Model);
	                        	displayPoducts(productList, "Products");
	                        	x = false;
	                        	
	                        	break;
	                    	case 5:
	                        	x = false;
	                        	
	                        	break;
	                        default:
	                            System.out.println("\nInvalid choice. Please try again.");
	                    }
	                    
                	}
                    
                    break;
                case 4:
                	cartApp.start();
                	
                    break;
                case 5:
                	System.out.print("\nEnter Admin Password: ");
                	pwd = scanner.next();
                	if(pwd.equals(ADMIN_PWD)) {
                		System.out.println("\nEnter Item Type: ");
                    	String type = scanner.next();
                		System.out.println("\nEnter Item Brand: ");
                    	String brand = scanner.next();
                		System.out.println("\nEnter Item Model: ");
                    	String model = scanner.next();
                		System.out.println("\nEnter Item Qty: ");
                    	int qty = scanner.nextInt();
                		System.out.println("\nEnter Item Price: ");
                    	Double price = scanner.nextDouble();
                    	
                    	productService.addProduct(type, brand, model, qty, price, false);
                    	System.out.println("\nProduct Added Successfully");
                	}
                	else {
                		System.out.println("\nInvalid Password!");
                	}
                	
                	break;
                case 6:
                	System.out.print("\nEnter Admin Password: ");
                	pwd = scanner.next();
                	if(pwd.equals(ADMIN_PWD)) {
                		System.out.println("\nEnter Item Id: ");
                    	String id = scanner.next();
                    	
                    	boolean removeStat = productService.removeProduct(id);
                    	if(removeStat) {
                    		System.out.println("\nProduct Removed Successfully");                    		
                    	}
                    	else {
                    		System.out.println("\nProduct Not Found");   
                    	}
                	}
                	else {
                		System.out.println("\nInvalid Password!");
                	}
                	
                	break;
                case 7:
                    System.out.println("\nExiting the Order Service application.");
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

}
