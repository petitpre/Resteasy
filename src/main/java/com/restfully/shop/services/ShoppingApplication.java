package com.restfully.shop.services;

import com.makitoo.Makitoo;
import com.makitoo.MakitooConfig;
import com.makitoo.internal.Config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/services")
public class ShoppingApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public ShoppingApplication() {

        Makitoo makitoo =  com.makitoo.MakitooJava.init(
                "https://dashboard.makitoo.com/rest",
                "testjaxrs-eb0d369a-168b-445f-b7de-6e41ce1de02e",   // Application ID
                "j068qk8j8npvas4fmcihu5f8ms",                     // Application token
                "0.0.1"                                           // Current version of your application
        );

        singletons.add(new CustomerResource());
        singletons.add(new OrderResource());
        singletons.add(new StoreResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
