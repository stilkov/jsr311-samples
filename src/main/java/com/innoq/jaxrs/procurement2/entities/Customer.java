package com.innoq.jaxrs.procurement2.entities;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String name;
    private static List<Customer> customers;

    public Customer(String name) {
        this.name = name;
    }

    static {
        customers = new ArrayList<Customer>();
        customers.add(new Customer("First Customer"));
        customers.add(new Customer("Second Customer"));
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
