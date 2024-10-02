package com.order_service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class OrderServiceActivator implements BundleActivator {
	
	ServiceRegistration orderPublishRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("OrderService bundle started");
        
        // Register the OrderService implementation with the OSGi service registry
        orderPublishRegistration = context.registerService(OrderServicePublish.class.getName(), new OrderServicePublishImpl(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("OrderService bundle stopped");
//        Unregister the service
        orderPublishRegistration.unregister();
    }

}