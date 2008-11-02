package com.innoq.jaxrs.procurement3;

import nu.xom.Element;
import nu.xom.Document;
import nu.xom.Serializer;

import java.io.OutputStream;
import java.io.IOException;

public class Utilities {
    public final static String NAMESPACE = "http://innoq.com/ns/procurement-demo";

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
        s.setIndent(4);
        s.setMaxLength(72);
        s.write(doc);
    }
}
