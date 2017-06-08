package com.dariuszpaluch;

import com.dariuszpaluch.dao.Context;
import com.dariuszpaluch.resources.DateParamConverterProvider;
import com.dariuszpaluch.utils.InitializeDataBase;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

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

        Context context = Context.getInstance();
        context.initialize();

        URI baseUri = UriBuilder.fromUri("http://localhost").port(9996).build();

        ResourceConfig config = new ResourceConfig().packages("com.dariuszpaluch").registerClasses(
                DeclarativeLinkingFeature.class,
                CustomHeaders.class
        );
        config.register(new DateParamConverterProvider("yyyy-MM-dd"));


        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        server.start();
    }
}
