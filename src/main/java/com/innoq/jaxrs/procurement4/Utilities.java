package com.innoq.jaxrs.procurement4;

import nu.xom.Element;
import nu.xom.Document;
import nu.xom.Serializer;

import javax.ws.rs.core.UriBuilder;
import java.io.OutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import com.innoq.jaxrs.procurement4.resources.CustomersResource;

public class Utilities {
    public final static String NAMESPACE = "http://innoq.com/ns/procurement-demo";
    public final static String BASEURI = "http://localhost:9998/procurement";

    public static URI customerPath(int id) {
        URI uri =  UriBuilder.fromUri(BASEURI).path(CustomersResource.class).path(Integer.toString(id)).build();
        return uri;
    }

    public static String elementToXmlString(Element elem) {
        Document doc = new Document(elem);
        OutputStream os = new StringBufferOutputStream();
        Serializer s = new Serializer(os);
        s.setIndent(4);
        s.setMaxLength(72);
        try {
            s.write(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return os.toString();
    }


    static void writeElementToStream(Element root, OutputStream entityStream) throws IOException {
        Document doc = new Document(root);
        OutputStream os = new StringBufferOutputStream();
        Serializer s = new Serializer(entityStream);
        s.setIndent(2);
        s.setMaxLength(90);
        s.write(doc);
    }
}
