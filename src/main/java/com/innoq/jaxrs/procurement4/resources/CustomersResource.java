package com.innoq.jaxrs.procurement4.resources;

import com.innoq.jaxrs.procurement4.entities.Customer;
import static com.innoq.jaxrs.procurement4.Utilities.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import java.util.List;


@Path("/procurement4/customers/")
public class CustomersResource {


    @GET @Produces("application/vnd.innoq.customers+xml")
    public List<Customer> getAsXml() {
        return Customer.findAll();
    }

    @POST @Consumes("application/vnd.innoq.customer+xml")
    public Response newCustomer(Customer c) {
        int id = Customer.add(c);
        return Response.created(customerPath(id)).build();
    }


    @Path("{id}")
    public CustomerResource customerById(@PathParam("id") int id) {
        return new CustomerResource(Customer.get(id));
    }

  
    @GET @Produces("text/plain")
    public String getAsPlainText() {
        return toString() +  "\n\n";
    }


}
