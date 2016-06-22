package com.restfully.shop.services;


import com.restfully.shop.domain.Customer;
import org.json.JSONObject;

import javax.ws.rs.client.ClientBuilder;

/**
 * Created by nicolas on 21/06/16.
 */
public class AllowService {

    public int searchAllowId(Customer customer) {
        return parseAllowed(search(customer.getFirstName())).getId();
    }

    private String search(String query) {
        return ClientBuilder.newClient().target("http://localhost:3000/search?value=" + query).request().get(String.class);
    }

    private AllowedValue parseAllowed(String str) {
        return new AllowedValue(str);
    }


    public static class AllowedValue {

        private String firstname;
        private String lastname;
        private int id;


        private AllowedValue() {
        }

        private AllowedValue(String strvalue) {
            JSONObject json = new JSONObject(strvalue);

            setFirstname(json.getString("firstname"));
            setLastname(json.getString("lastname"));
            setId(json.getInt("id"));
        }

        public String getFirstname() {
            return firstname;
        }

        public AllowedValue setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public String getLastname() {
            return lastname;
        }

        public AllowedValue setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public int getId() {
            return id;
        }

        public AllowedValue setId(int id) {
            this.id = id;
            return this;
        }
    }

}
