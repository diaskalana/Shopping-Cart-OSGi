package com.customer_service;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CustomerServiceActivator implements BundleActivator {
	
	ServiceRegistration publishServiceRegistration;
	
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("publisher start");
        try {
            CustomerServicePublishImpl customerService = new CustomerServicePublishImpl();
            publishServiceRegistration = bundleContext.registerService(
            		CustomerServicePublish.class.getName(), customerService, null);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception or log it appropriately.
        }
    }


    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("publisher Stop!");
        if (publishServiceRegistration != null) {
            publishServiceRegistration.unregister();
        }
        // Add additional cleanup steps if needed
    }


}
