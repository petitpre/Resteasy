package org.jboss.resteasy.test.nextgen.finegrain.methodparams;


import org.jboss.resteasy.client.jaxrs.ProxyBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.test.EmbeddedContainer;
import org.jboss.resteasy.util.HttpResponseCodes;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import static org.jboss.resteasy.test.TestPortProvider.generateBaseUrl;
import static org.jboss.resteasy.test.TestPortProvider.generateURL;
import static org.jboss.resteasy.util.HttpClient4xUtils.updateQuery;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class UriParamAsPrimitiveTest
{
   private static final float ASSERT_FLOAT_THRESHOLD = 0.000000001f;
   private static final double ASSERT_DOUBLE_THRESHOLD = 0.000000000000001d;

   private static ResteasyDeployment deployment;

   private static IResourceUriBoolean resourceUriBoolean;

   private static IResourceUriByte resourceUriByte;
   private static ResteasyClient client;

   @BeforeClass
   public static void before() throws Exception
   {
      deployment = EmbeddedContainer.start();
      deployment.getRegistry().addPerRequestResource(ResourceUriBoolean.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriByte.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriShort.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriInt.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriLong.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriFloat.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriDouble.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriBooleanWrapper.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriByteWrapper.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriShortWrapper.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriIntWrapper.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriLongWrapper.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriFloatWrapper.class);
      deployment.getRegistry().addPerRequestResource(ResourceUriDoubleWrapper.class);
      client = new ResteasyClientBuilder().build();
      resourceUriBoolean = ProxyBuilder.builder(IResourceUriBoolean.class, client.target(generateBaseUrl())).build();
      resourceUriByte = ProxyBuilder.builder(IResourceUriByte.class, client.target(generateBaseUrl())).build();
   }

   @AfterClass
   public static void after() throws Exception
   {
      client.close();
      EmbeddedContainer.stop();
   }

   @Path("/boolean/{arg}")
   public static class ResourceUriBoolean
   {
      @GET
      public String doGet(@PathParam("arg") boolean v)
      {
         Assert.assertEquals(true, v);
         return "content";
      }
   }

   @Path("/boolean/{arg}")
   public static interface IResourceUriBoolean
   {
      @GET
      public String doGet(@PathParam("arg") boolean v);
   }

   @Path("/byte/{arg}")
   public static class ResourceUriByte
   {
      @GET
      public String doGet(@PathParam("arg") byte v)
      {
         Assert.assertTrue(127 == v);
         return "content";
      }
   }

   @Path("/byte/{arg}")
   public static interface IResourceUriByte
   {
      @GET
      public String doGet(@PathParam("arg") byte v);
   }

   @Path("/short/{arg}")
   public static class ResourceUriShort
   {
      @GET
      public String doGet(@PathParam("arg") short v)
      {
         Assert.assertTrue(32767 == v);
         return "content";
      }
   }

   @Path("/int/{arg}")
   public static class ResourceUriInt
   {
      @GET
      public String doGet(@PathParam("arg") int v)
      {
         Assert.assertEquals(2147483647, v);
         return "content";
      }
   }

   @Path("/long/{arg}")
   public static class ResourceUriLong
   {
      @GET
      public String doGet(@PathParam("arg") long v)
      {
         Assert.assertEquals(9223372036854775807L, v);
         return "content";
      }
   }

   @Path("/float/{arg}")
   public static class ResourceUriFloat
   {
      @GET
      public String doGet(@PathParam("arg") float v)
      {
         Assert.assertEquals(3.14159265f, v, ASSERT_FLOAT_THRESHOLD);
         return "content";
      }
   }

   @Path("/double/{arg}")
   public static class ResourceUriDouble
   {
      @GET
      public String doGet(@PathParam("arg") double v)
      {
         Assert.assertEquals(3.14159265358979d, v, ASSERT_DOUBLE_THRESHOLD);
         return "content";
      }
   }

   @Path("/boolean/wrapper/{arg}")
   public static class ResourceUriBooleanWrapper
   {
      @GET
      public String doGet(@PathParam("arg") Boolean v)
      {
         Assert.assertEquals(true, v.booleanValue());
         return "content";
      }
   }

   @Path("/byte/wrapper/{arg}")
   public static class ResourceUriByteWrapper
   {
      @GET
      public String doGet(@PathParam("arg") Byte v)
      {
         Assert.assertTrue(127 == v.byteValue());
         return "content";
      }
   }

   @Path("/short/wrapper/{arg}")
   public static class ResourceUriShortWrapper
   {
      @GET
      public String doGet(@PathParam("arg") Short v)
      {
         Assert.assertTrue(32767 == v.shortValue());
         return "content";
      }
   }

   @Path("/int/wrapper/{arg}")
   public static class ResourceUriIntWrapper
   {
      @GET
      public String doGet(@PathParam("arg") Integer v)
      {
         Assert.assertEquals(2147483647, v.intValue());
         return "content";
      }
   }

   @Path("/long/wrapper/{arg}")
   public static class ResourceUriLongWrapper
   {
      @GET
      public String doGet(@PathParam("arg") Long v)
      {
         Assert.assertEquals(9223372036854775807L, v.longValue());
         return "content";
      }
   }

   @Path("/float/wrapper/{arg}")
   public static class ResourceUriFloatWrapper
   {
      @GET
      public String doGet(@PathParam("arg") Float v)
      {
         Assert.assertEquals(3.14159265f, v.floatValue(), ASSERT_FLOAT_THRESHOLD);
         return "content";
      }
   }

   @Path("/double/wrapper/{arg}")
   public static class ResourceUriDoubleWrapper
   {
      @GET
      public String doGet(@PathParam("arg") Double v)
      {
         Assert.assertEquals(3.14159265358979d, v.doubleValue(), ASSERT_DOUBLE_THRESHOLD);
         return "content";
      }
   }

   void _test(String type, String value)
   {
      {
         Invocation.Builder request = client.target(generateURL("/" + type + "/" + value)).request();
         try
         {
            Response response = request.get();
            Assert.assertEquals(HttpResponseCodes.SC_OK, response.getStatus());
            response.close();
         } catch (Exception e)
         {
            throw new RuntimeException(e);
         }
      }
      
      {
         Invocation.Builder request = client.target(generateURL("/" + type + "/wrapper/" + value)).request();
         try
         {
            Response response = request.get();
            Assert.assertEquals(HttpResponseCodes.SC_OK, response.getStatus());
            response.close();
         } catch (Exception e)
         {
            throw new RuntimeException(e);
         }
      }
   }

   @Test
   public void testGetBoolean()
   {
      _test("boolean", "true");
      resourceUriBoolean.doGet(true);
   }

   @Test
   public void testGetByte()
   {
      _test("byte", "127");
      resourceUriByte.doGet((byte) 127);
   }

   @Test
   public void testGetShort()
   {
      _test("short", "32767");
   }

   @Test
   public void testGetInt()
   {
      _test("int", "2147483647");
   }

   @Test
   public void testGetLong()
   {
      _test("long", "9223372036854775807");
   }

   @Test
   public void testGetFloat()
   {
      _test("float", "3.14159265");
   }

   @Test
   public void testGetDouble()
   {
      _test("double", "3.14159265358979");
   }

   public void testBadPrimitiveValue()
   {
      String uri = updateQuery(generateURL("/int/abcdef"), "int=abcdef");
      Invocation.Builder request = client.target(uri).request();
      try
      {
         Response response = request.get();
         Assert.assertEquals(HttpResponseCodes.SC_OK, response.getStatus());
         response.close();
      } catch (Exception e)
      {
         throw new RuntimeException(e);
      }
   }

   public void testBadPrimitiveWrapperValue()
   {
      String uri = updateQuery(generateURL("/int/wrapper/abcdef"), "int=abcdef");
      Invocation.Builder request = client.target(uri).request();
      try
      {
         Response response = request.get();
         Assert.assertEquals(HttpResponseCodes.SC_OK, response.getStatus());
         response.close();
      } catch (Exception e)
      {
         throw new RuntimeException(e);
      }
   }
}
