package com.innoq.jaxrs.procurement3.resources;

import com.innoq.jaxrs.procurement3.entities.Customer;
import static com.innoq.jaxrs.procurement3.Utilities.*;
import com.innoq.jaxrs.procurement3.Utilities;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import nu.xom.Element;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.XPathContext;

public class CustomerResource {
    @GET
    public Response get() {
        if (customer == null) return noSuchCustomer();
        return Response.ok().entity(customer).build();
    }

    @PUT
    public Response putAsXml(Customer c) {
        if (customer == null) return noSuchCustomer();
        try {
            customer.setName(c.getName());
            return Response.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity("Please send well-formed XML\n").type("text/plain").build();
        }

    }

    @DELETE
    public Response delete(@PathParam("id") int id) {
        Customer.delete(id);
        return Response.ok().build();
    }

    private Response noSuchCustomer() {
        return Response.status(404).entity("We don't have this object\n").type("text/plain").build();
    }

    private Customer customer;

    public CustomerResource(Customer customer) {
        this.customer = customer;
    }

}
