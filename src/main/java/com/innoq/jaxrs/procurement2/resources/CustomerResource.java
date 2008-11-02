package com.innoq.jaxrs.procurement2.resources;

import com.innoq.jaxrs.procurement2.entities.Customer;
import static com.innoq.jaxrs.procurement2.Utilities.*;
import com.innoq.jaxrs.procurement2.Utilities;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import nu.xom.Element;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.XPathContext;

public class CustomerResource {
    private Customer customer;

    public CustomerResource(Customer customer) {
        this.customer = customer;
    }

    @GET @Produces("application/vnd.innoq.customer+xml")
    public Response getAsXml() {
        if (customer == null) return noSuchCustomer();
        Element root = new Element("customer", NAMESPACE);
        Element name = new Element("name", NAMESPACE);
        name.appendChild(customer.getName());
        root.appendChild(name);
        return Response.ok(elementToXmlString(root)).build();
    }


    @PUT @Consumes("application/vnd.innoq.customer+xml")
    public Response putAsXml(String body) {
        if (customer == null) return noSuchCustomer();
        Builder b = new Builder();
        try {
            System.out.println("Received: " + body);
            Document doc = b.build(body, ".");
            String name = doc.query("/i:customer/i:name", new XPathContext("i", Utilities.NAMESPACE)).get(0).getValue();
            customer.setName(name);
            return Response.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).type("text/plain").entity("Please send well-formed XML\n").build();
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
}
