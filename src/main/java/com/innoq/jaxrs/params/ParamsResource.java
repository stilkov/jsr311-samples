package com.innoq.jaxrs.params;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;

@Path("/params/{segment1}")
public class ParamsResource {

    @GET @Path("/{segment2}")
    @Produces("text/plain")
    public String getParams(@PathParam("segment1") String segment1, 
    		                @PathParam("segment2") String segment2,
    		                @QueryParam("q") String q) {
        return "Segment 1: " + segment1 + "\n" +
               "Segment 2: " + segment2 + "\n" +
               "Query: " + q + "\n";
    }
}