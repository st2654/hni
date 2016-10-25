package org.hni.admin.service;

import java.util.Collection;
import java.util.Date;

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

@Component
@Path("/order")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Inject private OrderService orderService;
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Order getUser(@PathParam("id") Long id) {
		return orderService.get(id);
	}

	@GET
	@Path("/user/{id}/since/{startDate}")
	@Produces({MediaType.APPLICATION_JSON})
	public Collection<Order> getUser(@PathParam("id") Long id, @PathParam("startDate") Date startDate) {
		return orderService.get(new User(id), startDate);
	}

}
