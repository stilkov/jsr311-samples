package com.innoq.jaxrs.procurement3;

import com.innoq.jaxrs.procurement3.entities.Customer;
import static com.innoq.jaxrs.procurement3.Utilities.NAMESPACE;
import static com.innoq.jaxrs.procurement3.Utilities.writeElementToStream;

import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import nu.xom.Element;

@Provider
@Produces({"application/vnd.innoq.customer+xml", "text/plain"})
public class CustomerWriter implements MessageBodyWriter<Customer> {
    public void writeTo(Customer customer, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
    }

	public long getSize(Customer t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
        return Customer.class.isAssignableFrom(type);
	}

	public void writeTo(Customer customer, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
        if (mediaType.getType().compareTo("application") == 0 &&
                mediaType.getSubtype().compareTo("vnd.innoq.customer+xml") == 0) {
            Element root = new Element("customer", NAMESPACE);
            Element name = new Element("name", NAMESPACE);
            name.appendChild(customer.getName());
            root.appendChild(name);
            writeElementToStream(root, entityStream);
        } else if (mediaType.getType().compareTo("text") == 0 &&
                mediaType.getSubtype().compareTo("plain") == 0) {
            OutputStreamWriter osw = new OutputStreamWriter(entityStream);
            osw.write("Customer: \"");
            osw.write(customer.getName());
            osw.write("\"\n");
            osw.close();
        }
	}

}
