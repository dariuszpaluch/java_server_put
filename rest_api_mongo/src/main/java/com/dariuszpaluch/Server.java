package com.dariuszpaluch;

import com.dariuszpaluch.utils.InitializeDataBase;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.mongodb.morphia.Morphia;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            InitializeDataBase.initializeStudents();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        URI baseUri = UriBuilder.fromUri("http://localhost").port(9998).build();

        ResourceConfig config = new ResourceConfig().packages("com.dariuszpaluch").registerClasses(
                DeclarativeLinkingFeature.class
        );

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        server.start();
    }
}
