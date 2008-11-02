package com.innoq.jaxrs.procurement3.entities;

import com.innoq.jaxrs.procurement3.CustomerList;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String name;
    private static CustomerList customers;

    public Customer(String name) {
        this.name = name;
    }

    static {
        customers = new CustomerList();
        customers.add(new Customer("First Customer"));
        customers.add(new Customer("Second Customer"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
    	return Integer.toString(customers.indexOf(this));
    }

    public static CustomerList findAll() {
        return getCustomers();
    }

    public static CustomerList getCustomers() {
        return customers;
    }
    public static void deleteAll() {
        customers.clear();
    }

    public static int add(Customer c) {
        customers.add(c);
        return customers.size(); 
    }

    public static Customer get(int id) {
        if (id >= customers.size() || id < 0)
            return null;
        return customers.get(id);
    }

    public static void delete(int id) {
        if (id < customers.size() && id > 0)
            customers.set(id, null);
    }
    
}
