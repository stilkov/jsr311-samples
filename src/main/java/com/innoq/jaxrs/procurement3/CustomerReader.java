package com.innoq.jaxrs.procurement3;

import com.innoq.jaxrs.procurement3.entities.Customer;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;

import java.io.InputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.XPathContext;


@Provider
@Consumes("application/vnd.innoq.customer+xml")
public class CustomerReader implements MessageBodyReader<Customer> {

	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
        return Customer.class.isAssignableFrom(type);
	}

	public Customer readFrom(Class<Customer> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream inputStream)
			throws IOException, WebApplicationException {
        Customer customer = null;
        Builder b = new Builder();
        Document doc = null;
        try {
            doc = b.build(inputStream);
            String name = doc.query("/i:customer/i:name", new XPathContext("i", Utilities.NAMESPACE)).get(0).getValue();
            customer = new Customer(name);
        } catch (Exception e) {
            IOException ioex = new IOException(e.getMessage());
            ioex.initCause(e);
            throw ioex;
        }
        return customer;
	}
}
