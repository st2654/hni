package org.hni.admin.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hni.common.DateUtils;
import org.hni.order.om.Order;
import org.hni.order.service.OrderService;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/orders", description = "Operations on Orders and OrderItems")
@Component
@Path("/orders")
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
	public Order getOrder(@PathParam("id") Long id) {
		return orderService.get(id);
	}

	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Creates a new order or saves the order with the given id"
		, notes = "An order without an ID field will be created"
		, response = Order.class
		, responseContainer = "")
	public Order saveOrder(Order order) {
		return orderService.save(order);
	}

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Deletes the order with the given id"
		, notes = ""
		, response = Order.class
		, responseContainer = "")
	public Order getDelete(@PathParam("id") Long id) {
		return orderService.delete(new Order(id));
	}

	@GET
	@Path("/users/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Returns a collection of orders for the given user from given date until current date"
	, notes = "accepted date formats yyyy-mm-dd, yyyy/mm/dd, mm-dd-yyyy, mm/dd/yyyy"
	, response = Order.class
	, responseContainer = "")
	public Collection<Order> getUserOrdersSinceDate(@PathParam("id") Long id, @QueryParam("startDate") String startDate) {
		
		return orderService.get(new User(id), DateUtils.parseDate(startDate));
	}

	@GET
	@Path("/users/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Returns a collection of orders for the given user between the given dates"
	, notes = "accepted date formats yyyy-mm-dd, yyyy/mm/dd, mm-dd-yyyy, mm/dd/yyyy"
	, response = Order.class
	, responseContainer = "")
	public Collection<Order> getUserOrdersBetweenDates(@PathParam("id") Long id, @QueryParam("startDate") String startDate , @QueryParam("endDate") String endDate) {
		
		return orderService.get(new User(id), DateUtils.parseDate(startDate), DateUtils.parseDate(endDate));
	}


}
