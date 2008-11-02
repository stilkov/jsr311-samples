package com.innoq.jaxrs.procurement1;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.ContainerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Procurement1 {

    public static void main(String[] args) throws IOException {


        //HttpHandler handler = ContainerFactory.createContainer(
        //        HttpHandler.class,
        //        OrdersResource.class);

        HttpServer server = HttpServer.create(new InetSocketAddress(9998), 0);
        server.createContext("/",
                ContainerFactory.createContainer(HttpHandler.class,
                "com.innoq.jaxrs.procurement1.resources"));
        server.setExecutor(null);
        server.start();

        System.out.println("Server P1 running");
        System.out.println("Visit: http://localhost:9998/procurement/{orders|customers}");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

}
