package com.innoq.jaxrs.procurement3;

import nu.xom.Attribute;
import nu.xom.Element;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static com.innoq.jaxrs.procurement3.Utilities.NAMESPACE;
import static com.innoq.jaxrs.procurement3.Utilities.writeElementToStream;
import com.innoq.jaxrs.procurement3.entities.Customer;
import com.innoq.jaxrs.procurement3.resources.CustomersResource;

@Provider
@Produces("application/vnd.innoq.customers+xml")
public class CustomerListWriter implements MessageBodyWriter<CustomerList> {
	public long getSize(CustomerList t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return CustomerList.class == type;
	}

	public void writeTo(CustomerList customers, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
        Element root = new Element("customers", NAMESPACE);
        for (Customer customer : customers) {
            if (customer != null) {
                Element customerElement = new Element("customer", NAMESPACE);
                customerElement.addAttribute(new Attribute("ref", CustomersResource.uriFor(customer)));
                customerElement.appendChild(customer.getName());
                root.appendChild(customerElement);
            }
        }

        writeElementToStream(root, entityStream);
	}


}
