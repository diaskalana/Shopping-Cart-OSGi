package com.shoppingcartapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.product_service.Product;
import com.product_service.ProductServicePublish;
import com.shoppingcartservice.CartItem;
import com.shoppingcartservice.CartServicePublish;

public class CartAppImpl implements CartApp{

	private CartServicePublish cartService;
	private ProductServicePublish productService;
	
	public CartAppImpl(CartServicePublish cartService , ProductServicePublish productService) {
		this.productService = productService;
		this.cartService = cartService;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		interactWithUser();
	}
	
	private void interactWithUser() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n=== Shopping Cart Menu ===");
			System.out.println("1. Add Items to Cart");
			System.out.println("2. Remove Items from Cart");
			System.out.println("3. Show All Items in the Cart");
			System.out.println("4. Show All Products");
			System.out.println("5. Exit");

			System.out.print("\nEnter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.println("\n=== Add Items to Cart ===");
				System.out.print("Enter Product ID: ");
				String productId = scanner.nextLine();
				
				if (productService.getProductDetails(productId) != null) {
					
					Product product = productService.getProductDetails(productId);
					System.out.println("( Available max quantity for this product : "+ product.getQty()+" )");
					System.out.print("Enter Item quantity: ");
					Integer itemQty = scanner.nextInt();
					
					if(itemQty <= product.getQty()) {
						scanner.nextLine();
						Double itemPrice = product.getPrice() * itemQty;
						String itemName = product.getBrand() + "-" + product.getName() + "-" + product.getModel();
						CartItem item = new CartItem(1, itemName, itemQty, itemPrice);
						cartService.addItem(item);
						
					} else {
						cartService.triggerCartUpdate("Enter valid quantity & Try Again", 1);
					}
				} else {
					cartService.triggerCartUpdate("Enter valid product ID & Try Again", 1);
				}
				break;
			case 2:
				System.out.println("\n=== Remove Items from Cart ===");
				System.out.print("Enter item ID to remove: ");
				int idToRemove = scanner.nextInt();
				cartService.removeItem(idToRemove);
				break;
			case 3:
				System.out.println("\n=== All Items in the Cart ===");
				List<CartItem> allItems = cartService.getCartItems();
				if (allItems.isEmpty()) {
					System.out.println("No Items found.");
				} else {
					System.out.println(formatCartList(allItems));
				}
				break;
			case 4:
				ArrayList<Product> productList = new ArrayList<Product>();
				productList = productService.getAllProducts();
            	displayPoducts(productList, "All Products");
            	break;
			case 5:
				System.out.println("\nExiting the Shopping Cart application.");
				return;
			default:
//				System.out.println("\nInvalid choice. Please try again.");
				System.out.println();
				cartService.triggerCartUpdate("Invalid choice. Please try again.", 1);
			}
		}
	}

	private String formatCartList(List<CartItem> items) {
		StringBuilder builder = new StringBuilder();
		builder.append("+------------+-----------------------------------------------+------------+--------------+\n");
		builder.append(String.format("| %-10s | %-45s | %-10s | %-12s |\n", "Item ID", "Item Name", "Item Qty", "Price"));
		builder.append("+------------+-----------------------------------------------+------------+--------------+\n");

		for (CartItem item : items) {
			builder.append(String.format("| %-10d | %-45s | %-10d | $ %-10.2f |\n", item.getItemId(), item.getItemName(),
					item.getQuantity(), item.getPrice()));
		}

		builder.append("+------------+-----------------------------------------------+------------+--------------+\n");
		return builder.toString();
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

}
