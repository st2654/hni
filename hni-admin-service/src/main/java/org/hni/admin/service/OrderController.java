package org.hni.admin.service;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hni.order.om.Order;
import org.hni.order.service.OrderService;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/order", description = "Operations on Orders and OrderItems")
@Component
@Path("/order")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Inject private OrderService orderService;
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Returns the order with the given id"
		, notes = ""
		, response = Order.class
		, responseContainer = "")
	public Order getUser(@PathParam("id") Long id) {
		return orderService.get(id);
	}

	// TODO: add date
	@GET
	@Path("/user/{id}/since")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Returns a collection of orders for the given user for the past 3 days"
	, notes = "will add a date param to this in the future"
	, response = Order.class
	, responseContainer = "")
	public Collection<Order> getUserOrdersSinceADate(@PathParam("id") Long id /*, @PathParam("startDate") Date startDate*/) {
		LocalDateTime startDatex = LocalDateTime.now().minusDays(3);
		return orderService.get(new User(id), startDatex);
	}

}
