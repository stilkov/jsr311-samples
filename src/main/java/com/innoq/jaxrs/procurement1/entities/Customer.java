package com.innoq.jaxrs.procurement1.entities;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String name;
    private static List<Customer> customers;

    public Customer(String name) {
        this.name = name;
    }

    private static List<Customer> CUSTOMERS;

    static {
        CUSTOMERS = new ArrayList<Customer>();
        CUSTOMERS.add(new Customer("First Customer"));
        CUSTOMERS.add(new Customer("Second Customer"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Customer> findAll() {
        return getCustomers();
    }

    public static List<Customer> getCustomers() {
        if (customers == null) {
            customers = CUSTOMERS;
        }
        return customers;
    }
    public static void deleteAll() {
        customers.clear();
    }

    public static int add(Customer c) {
        getCustomers().add(c);
        return getCustomers().size(); 
    }
}
