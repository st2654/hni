package org.hni.admin;

import javax.ws.rs.ApplicationPath;

import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.hni.admin.filter.ResponseCorsFilter;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("api/v1") /* this is the servlet mapping */
public class Application extends ResourceConfig {

    public Application() {
        packages("org.hni");
        register(JacksonFeature.class);
        register(ResponseCorsFilter.class);
        
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        String host = String.format("localhost:%s", StringUtils.defaultIfBlank(System.getProperty("port"), "8080"));
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.5.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost(host);
        beanConfig.setBasePath("/hni-admin/api/v1");
        beanConfig.setResourcePackage("org.hni");
        beanConfig.setScan(true);
     
    }
}
