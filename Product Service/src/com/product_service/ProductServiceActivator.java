package com.product_service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ProductServiceActivator implements BundleActivator {

	ServiceRegistration publishProductServiceRegistration;
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Product Publisher Started");
		ProductServicePublish publisherService = new ProductServicePublishImpl();
		publishProductServiceRegistration = context.registerService(
				ProductServicePublish.class.getName(), publisherService, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Product Publisher Stoped");
		publishProductServiceRegistration.unregister();
	}

}
