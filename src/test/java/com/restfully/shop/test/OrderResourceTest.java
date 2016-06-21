package com.restfully.shop.test;

import com.restfully.shop.domain.Customer;
import com.restfully.shop.domain.LineItem;
import com.restfully.shop.domain.Order;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class OrderResourceTest
{
   private static Client client;

   @BeforeClass
   public static void initClient()
   {
      client = ClientBuilder.newClient();
   }

   @AfterClass
   public static void closeClient()
   {
      client.close();
   }

   @Test
   public void testCreateCancelPurge() throws Exception
   {
      String base = "http://localhost:8080/services/shop";
      Response response = client.target(base).request().head();

      Link customers = response.getLink("customers");
      response.close();

      System.out.println("** Create a customer through this URL: " + customers.getUri().toString());

      Customer customer = new Customer();
      customer.setFirstName("Bill");
      customer.setLastName("Burke");
      customer.setStreet("10 Somewhere Street");
      customer.setCity("Westford");
      customer.setState("MA");
      customer.setZip("01711");
      customer.setCountry("USA");

      response = client.target(customers).request().post(Entity.xml(customer));
      Assert.assertEquals(201, response.getStatus());
      response.close();



   }
}
