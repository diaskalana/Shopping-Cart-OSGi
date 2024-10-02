package com.shop;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.customer_app.CustomerApp;
import com.order_app.OrderApp;
import com.product_app.ProductApp;
import com.shoppingcartapp.CartApp;

public class Activator implements BundleActivator {


    private ServiceReference<ProductApp> productAppServiceReference;
    private ServiceReference<OrderApp> orderAppServiceReference;
    private ServiceReference<CartApp> cartAppServiceReference;
    private ServiceReference<CustomerApp> customerAppServiceReference;
    private ProductApp productApp;
    private OrderApp orderApp;
    private CartApp cartApp;
    private CustomerApp customerApp;

	public void start(BundleContext context) throws Exception {
		
		productAppServiceReference = context.getServiceReference(ProductApp.class);
		productApp = context.getService(productAppServiceReference);
		
		orderAppServiceReference = context.getServiceReference(OrderApp.class);
		orderApp = context.getService(orderAppServiceReference);
		
		cartAppServiceReference = context.getServiceReference(CartApp.class);
		cartApp = context.getService(cartAppServiceReference);
		
        customerAppServiceReference = context.getServiceReference(CustomerApp.class);  // Obtain reference for CustomerApp
        customerApp = context.getService(customerAppServiceReference);
		
		Welcome();
	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(productAppServiceReference);
		context.ungetService(orderAppServiceReference);
        context.ungetService(cartAppServiceReference);
        context.ungetService(customerAppServiceReference); 
	}
	
	private void Welcome() {
		System.out.println("Welcome to Aparell");
		System.out.println("Begin your shopping journey with us");
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
            System.out.println("\n=== Aparell Shopping Service Menu ===");
            System.out.println("1. Product menu");
            System.out.println("2. Cart menu");
            System.out.println("3. Order Menu");
            System.out.println("4. Profile Menu");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            String choice = scanner.next();

            switch (choice) {
                case "1":
                	productApp.start();
                	
                    break;
                case "2":
                	cartApp.start();
                	
                    break;
                case "3":
                	orderApp.start();
                    
                    break;
                case "4":
                    customerApp.start();  // Now it's safe to call start method on customerApp
                    break;
                case "5":
                    System.out.println("\nExiting Aparell Shop.");
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
	}

}
