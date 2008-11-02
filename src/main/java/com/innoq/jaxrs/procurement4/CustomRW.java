package com.innoq.jaxrs.procurement4;

import com.innoq.jaxrs.procurement4.entities.Customer;
import static com.innoq.jaxrs.procurement4.Utilities.NAMESPACE;
import static com.innoq.jaxrs.procurement4.Utilities.writeElementToStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.XPathContext;
import nu.xom.Element;

@Provider
//@Produces({ "application/vnd.innoq.customers+xml", "application/vnd.innoq.customers+xml" })
//@Consumes("application/vnd.innoq.customer+xml")
public class CustomRW implements MessageBodyWriter, MessageBodyReader  {
	public boolean isWriteable(Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
        return Customer.class.isAssignableFrom(type);
                // || CustomerList.class.isAssignableFrom(type);
    }

	public boolean isReadable(Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return Customer.class.isAssignableFrom(type);
    }

    public long getSize(Object o) {
        return -1;  //To change body of implemented methods use File | Settings | File Templates.
    }

	public void writeTo(Object o, Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap httpHeaders, OutputStream outputStream)
			throws IOException, WebApplicationException {
       if (o instanceof Customer) {
            Customer customer = (Customer)o;
            if (mediaType.getType().compareTo("application") == 0 &&
                    mediaType.getSubtype().compareTo("vnd.innoq.customer+xml") == 0) {
                Element root = new Element("customer", NAMESPACE);
                Element name = new Element("name", NAMESPACE);
                name.appendChild(customer.getName());
                root.appendChild(name);
                writeElementToStream(root, outputStream);
            } else if (mediaType.getType().compareTo("text") == 0 &&
                    mediaType.getSubtype().compareTo("plain") == 0) {
                OutputStreamWriter osw = new OutputStreamWriter(outputStream);
                osw.write("Customer: \"");
                osw.write(customer.getName());
                osw.write("\"\n");
                osw.close();
            }
        } else if (o instanceof List) {
            List<Customer> customers = (List<Customer>) o;
            if (mediaType.getType().compareTo("application") == 0 &&
                    mediaType.getSubtype().compareTo("vnd.innoq.customer+xml") == 0) {
                Element root = new Element("customers", NAMESPACE);
                for (Customer customer : customers) {
                    if (customer != null) {
                        Element customerElement = new Element("customer", NAMESPACE);
                        customerElement.appendChild(customer.getName());
                        root.appendChild(customerElement);
                    }
                }
                writeElementToStream(root, outputStream);
            } else if (mediaType.getType().compareTo("text") == 0 &&
                    mediaType.getSubtype().compareTo("plain") == 0) {
                OutputStreamWriter osw = new OutputStreamWriter(outputStream);
                osw.write("Customer list: \"");
                for (Customer customer : customers) {
                    osw.write(customer.getName());
                    osw.write("\"\n");
                    osw.close();
                }
            }
        }
    }

	public Object readFrom(Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap httpHeaders, InputStream inputStream)
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

	public long getSize(Object t, Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return 0;
	}


}
