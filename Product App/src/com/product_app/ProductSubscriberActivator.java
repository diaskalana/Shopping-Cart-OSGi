package com.product_app;

import com.product_service.Product;
import com.product_service.ProductServicePublish;
import com.shoppingcartapp.CartApp;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class ProductSubscriberActivator implements BundleActivator {

    private ServiceReference<ProductServicePublish> productServiceReference;
    private ProductServicePublish ProductServicePublish;

    private ServiceReference<CartApp> cartAppServiceReference;
    private CartApp cartApp;

    ServiceRegistration appRegistration;
    
    @Override
    public void start(BundleContext context) throws Exception {

        // Get a reference to the OrderService
    	productServiceReference = context.getServiceReference(ProductServicePublish.class);
    	ProductServicePublish = context.getService(productServiceReference);
		
		cartAppServiceReference = context.getServiceReference(CartApp.class);
		cartApp = context.getService(cartAppServiceReference);
    	
    	ProductApp productApp = new ProductAppImpl(ProductServicePublish, cartApp);
    	appRegistration = context.registerService(ProductApp.class.getName(), productApp, null);
    	
    	//displayPoducts(ProductServicePublish);
    	
        // Start user interaction
        
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unget the service reference
        context.ungetService(productServiceReference);
    }

}
