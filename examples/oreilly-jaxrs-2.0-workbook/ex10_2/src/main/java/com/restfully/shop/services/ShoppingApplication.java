package com.restfully.shop.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/services")
public class ShoppingApplication extends Application
{
   private Set<Object> singletons = new HashSet<Object>();

   public ShoppingApplication()
   {

      com.makitoo.MakitooJava.init(
              "https://dashboard.makitoo.com/rest",
              "testjaxrs-a714aa71-933e-4e36-bd0f-90787bcf2c20",   // Application ID
              "2d0rr521p3vfr6h8tmjgrnujlp",                     // Application token
              "0.0.1"                                           // Current version of your application
      );

      singletons.add(new CustomerResource());
      singletons.add(new OrderResource());
      singletons.add(new StoreResource());
   }

   @Override
   public Set<Object> getSingletons()
   {
      return singletons;
   }
}
