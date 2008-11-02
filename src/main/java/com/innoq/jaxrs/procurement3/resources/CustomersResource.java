package com.innoq.jaxrs.procurement3.resources;

import com.innoq.jaxrs.procurement3.entities.Customer;
import static com.innoq.jaxrs.procurement3.Utilities.*;
import com.innoq.jaxrs.procurement3.Utilities;
import com.innoq.jaxrs.procurement3.CustomerList;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import java.util.List;

import nu.xom.*;


@Path("/procurement3/customers/")
public class CustomersResource {


    @GET
    public CustomerList getAsXml() {
        return Customer.findAll();
    }

    @POST
    public Response newCustomer(Customer c) {
        Customer.add(c);
        return Response.ok().build();
    }


    @Path("{id}")
    public CustomerResource customerById(@PathParam("id") int id) {
        return new CustomerResource(Customer.get(id));
    }

  
    @GET @Produces("text/plain")
    public String getAsPlainText() {
        return toString() +  "\n\n";
    }

	public static String uriFor(Customer customer) {
		return UriBuilder.fromResource(CustomersResource.class).path(CustomersResource.class, "customerById").build(customer.getId()).toString();
	}


}
