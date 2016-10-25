package org.hni.admin;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.hni.admin.filter.ResponseCorsFilter;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("api") /* this is the servlet mapping */
public class Application extends ResourceConfig {

    public Application() {
        packages("org.hni");
        register(JacksonFeature.class);
        register(ResponseCorsFilter.class);
        
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.5.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/service/api");
        beanConfig.setResourcePackage("org.hni");
        beanConfig.setScan(true);
     
    }
}
