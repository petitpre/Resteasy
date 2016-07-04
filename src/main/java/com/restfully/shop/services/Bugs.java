package com.restfully.shop.services;

import com.restfully.shop.domain.Customer;
import com.restfully.shop.domain.Customers;
import org.jboss.resteasy.annotations.providers.jaxb.Formatted;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by nicolas on 04/07/16.
 */
@Path("/bugs")
public class Bugs {

    private int count = 0;

    private String exception(String name) {
        count++;
        System.out.println(name.toLowerCase());
        return name.toLowerCase();
    }

    @GET
    @Path("/handled")
    @Produces({"application/xml", "application/json"})
    @Formatted
    public Response handled() {
        try {
            exception(null);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return Response.ok().build();
    }


    @GET
    @Path("/unhandled")
    @Produces({"application/xml", "application/json"})
    @Formatted
    public Response unhandled() {
        return Response.ok(exception(null)).build();
    }

    @GET
    @Path("/loop")
    @Produces({"application/xml", "application/json"})
    @Formatted
    public Response loop() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            TimeUnit.MILLISECONDS.sleep(2);
        }
        return Response.ok().build();
    }

}
