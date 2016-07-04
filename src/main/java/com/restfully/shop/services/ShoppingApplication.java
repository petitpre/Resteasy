package com.restfully.shop.services;

import com.makitoo.Makitoo;
import com.makitoo.MakitooConfig;
import com.makitoo.internal.Config;
import com.makitoo.internal.DefaultMakitoo;
import com.makitoo.probes.ContextEnhancerProbe;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationPath("/services")
public class ShoppingApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public ShoppingApplication() {

//        com.makitoo.MakitooJava.init(
//                "https://dashboard.makitoo.com/rest",
//                "testjaxrs-e5b9cad4-ae73-4147-b280-a793b40d313e",   // Application ID
//                "cejgcf5putasgd4kb6ptnl7iu2",                     // Application token
//                "0.0.1"                                           // Current version of your application
//        );
        Makitoo makitoo = com.makitoo.MakitooJava.init(
                "http://ec2-52-27-189-237.us-west-2.compute.amazonaws.com/rest",
                "testjaxrs-5d8a4667-5623-427e-bff6-955833b45677",   // Application ID
                "knigco5168909rvomaegsk2qao",                     // Application token
                "0.0.2"                                           // Current version of your application
        );

        ((Config)makitoo.getConfig()).recursiveContextSize.set(2);
        ((Config)makitoo.getConfig()).contextSize.set(20);


        singletons.add(new CustomerResource());
        singletons.add(new OrderResource());
        singletons.add(new StoreResource());
        singletons.add(new Bugs());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
