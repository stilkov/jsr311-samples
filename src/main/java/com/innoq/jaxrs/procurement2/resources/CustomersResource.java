package com.innoq.jaxrs.procurement2.resources;

import com.innoq.jaxrs.procurement2.entities.Customer;
import static com.innoq.jaxrs.procurement2.Utilities.*;
import com.innoq.jaxrs.procurement2.Utilities;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import nu.xom.*;


@Path("/procurement2/customers/")
public class CustomersResource {

    @GET @Produces("text/plain")
    public String getAsPlainText() {
        return toString() +  "\n\n";
    }

    @GET @Produces("application/vnd.innoq.customers+xml")
    public String getAsXml() {
        List<Customer> customers = Customer.findAll();
        Element root = new Element("customers", Utilities.NAMESPACE);
        for (Customer customer : customers) {
            Element customerElement = new Element("customer", Utilities.NAMESPACE);
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
                    new XPathContext("i", Utilities.NAMESPACE)).get(0).getValue());
            Customer.add(c);
            return Response.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity("Please send well-formed XML\n").type("text/plain").build();
        }

    }

    @Path("{id}")
    public CustomerResource customerById(@PathParam("id") int id) {
        return new CustomerResource(Customer.get(id));
    }




}
