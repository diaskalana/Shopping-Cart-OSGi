package com.shoppingcartapp;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.product_service.Product;
import com.product_service.ProductServicePublish;

import com.shoppingcartservice.CartItem;
import com.shoppingcartservice.CartServicePublish;

public class CartSubscriberActivator implements BundleActivator {

	private ServiceReference<CartServicePublish> serviceReference;
	private CartServicePublish ServicePublish;
	
	private ServiceReference<ProductServicePublish> productServiceReference;
    private ProductServicePublish ProductServicePublish;

    ServiceRegistration appRegistration;
    
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("ShoppingCartService UI bundle started");

		// Get a reference to the CartService
		serviceReference = context.getServiceReference(CartServicePublish.class);
		ServicePublish = context.getService(serviceReference);
		
		//Get a reference to the ProductService
		productServiceReference = context.getServiceReference(ProductServicePublish.class);
    	ProductServicePublish = context.getService(productServiceReference);

		CartApp cartApp = new CartAppImpl(ServicePublish, ProductServicePublish);
		appRegistration = context.registerService(CartApp.class.getName(), cartApp, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("ShoppingCartService UI bundle stopped");

		// Unget the service reference
		context.ungetService(serviceReference);
	}

	
	
	

}
