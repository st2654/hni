package org.hni.admin.service;

import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hni.order.om.Order;
import org.hni.provider.om.Provider;
import org.hni.provider.om.ProviderLocation;
import org.hni.provider.service.MenuService;
import org.hni.provider.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/providers", description = "Operations on Providers and ProviderLocations")
@Component
@Path("/providers")
public class ProviderController {
	private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);
	
	@Inject private ProviderService providerService;
	@Inject private MenuService menuService;
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Returns the Provider with the given id"
		, notes = ""
		, response = Provider.class
		, responseContainer = "")
	public Provider getProvider(@PathParam("id") Long id) {
		return providerService.get(id);
	}

	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Creates a new Provider or saves the Provider with the given id"
		, notes = "An Provider without an ID field will be created"
		, response = Provider.class
		, responseContainer = "")
	public Provider saveProvider(Provider provider) {
		return providerService.save(provider);
	}

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Deletes the Provider with the given id"
		, notes = ""
		, response = Provider.class
		, responseContainer = "")
	public Provider getDelete(@PathParam("id") Long id) {
		return providerService.delete(new Provider(id));
	}

	@GET
	@Path("/{id}/providerLocations")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Returns a collection of ProviderLocations for the given Provider"
	, notes = ""
	, response = ProviderLocation.class
	, responseContainer = "")
	public Collection<ProviderLocation> getProviderLocations(@PathParam("id") Long id) {
		// TODO
		return Collections.emptyList();
	}

	@POST
	@Path("/{id}/providerLocations")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Adds a ProviderLocation for the given Provider"
	, notes = ""
	, response = ProviderLocation.class
	, responseContainer = "")
	public ProviderLocation addProviderLocation(@PathParam("id") Long id, ProviderLocation providerLocation) {
		// TODO
		return null;
	}

	@DELETE
	@Path("/{id}/providerLocations/{plid}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Deletes given ProviderLocation for the given Provider"
	, notes = ""
	, response = ProviderLocation.class
	, responseContainer = "")
	public ProviderLocation addProviderLocation(@PathParam("id") Long id, @PathParam("plid") Long plid) {
		// TODO
		return null;
	}
}
