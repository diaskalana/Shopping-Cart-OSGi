package com.customer_app;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.customer_service.CustomerServicePublish;



public class CustomerSubscriberActivator implements BundleActivator {
	
    private ServiceReference<CustomerServicePublish> serviceReference;
    private CustomerServicePublish CustomerServicePublish;
    ServiceRegistration appRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Start Subscribe Service");
        serviceReference = bundleContext.getServiceReference(CustomerServicePublish.class);
        CustomerServicePublish = bundleContext.getService(serviceReference);
        
        CustomerApp customerApp = new CustomerAppImpl(CustomerServicePublish);
        appRegistration = bundleContext.registerService(CustomerApp.class.getName(),customerApp, null);

	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Good bye!");
		bundleContext.ungetService(serviceReference);

	}

}
