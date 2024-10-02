package com.order_app;

import com.order_service.Order;
import com.order_service.OrderServicePublish;


import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class OrderSubscriberActivator implements BundleActivator {


    private ServiceReference<OrderServicePublish> serviceReference;
    private OrderServicePublish OrderServicePublish;
    ServiceRegistration appRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("OrderService UI bundle started");

        // Get a reference to the OrderService
        serviceReference = context.getServiceReference(OrderServicePublish.class);
        OrderServicePublish = context.getService(serviceReference);
        
        OrderApp orderApp = new OrderAppImpl(OrderServicePublish);
        appRegistration = context.registerService(OrderApp.class.getName(),orderApp, null);
        

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("OrderService UI bundle stopped");

        // Unget the service reference
        context.ungetService(serviceReference);
    }

   


}
