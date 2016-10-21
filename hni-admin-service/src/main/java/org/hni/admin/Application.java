package org.hni.admin;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.hni.admin.filter.ResponseCorsFilter;

@ApplicationPath("api") /* this is the servlet mapping */
public class Application extends ResourceConfig {

    public Application() {
        packages("org.hni.admin");
        register(JacksonFeature.class);
        register(ResponseCorsFilter.class);
    }
}
