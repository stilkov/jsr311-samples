package com.innoq.jaxrs.procurement1.resources;

import com.innoq.jaxrs.procurement1.entities.Customer;
import static com.innoq.jaxrs.procurement1.Utilities.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import nu.xom.*;


@Path("/procurement1/customers/")
public class CustomersResource {
    private final static String ns = "http://innoq.com/ns/procurement-demo";


    @GET
    @Produces("application/vnd.innoq.customers+xml")
    public String getAsXml() {
        List<Customer> customers = Customer.findAll();
        Element root = new Element("customers", ns);
        for (Customer customer : customers) {
            Element customerElement = new Element("customer", ns);
            customerElement.appendChild(customer.getName());
            root.appendChild(customerElement);
        }
        return elementToXmlString(root);
    }

    @POST @Consumes("application/vnd.innoq.customer+xml")
    public Response newCustomer(String body) {
        Builder b = new Builder();
        try {
            System.out.println("Received: " + body);
            Document doc = b.build(body, ".");
            Customer c = new Customer(doc.query("/i:customer/i:name", 
                    new XPathContext("i", ns)).get(0).getValue());
            Customer.add(c);
            return Response.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity("Please send well-formed XML\n").type("text/plain").build();
        }

    }


    @GET @Produces("text/plain")
    public String getAsPlainText() {
        return toString() +  "\n\n";
    }


}
