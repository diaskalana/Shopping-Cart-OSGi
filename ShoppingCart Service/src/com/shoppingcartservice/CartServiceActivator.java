package com.shoppingcartservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CartServiceActivator implements BundleActivator {

	private ServiceRegistration<CartServicePublish> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("ShoppingCartService bundle started");

		CartServicePublish shoppingCartService = new CartServicePublishImpl();
		registration = context.registerService(CartServicePublish.class, shoppingCartService, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("ShoppingCartService bundle stopped");

		registration.unregister();
	}

}
