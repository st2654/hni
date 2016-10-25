package org.hni.order.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;

import org.apache.log4j.BasicConfigurator;
import org.hni.order.om.Order;
import org.hni.order.om.OrderItem;
import org.hni.provider.om.MenuItem;
import org.hni.provider.om.ProviderLocation;
import org.hni.user.om.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-applicationContext.xml"} )
@Transactional
public class TestOrderService {
	
	@Inject private OrderService orderService;
	
	public TestOrderService() {
		BasicConfigurator.configure();
	}

	@Test
	public void testAddOrder() {
		Order order = new Order();
		
		order.setCreatedById(1L);
		order.setOrderDate(new Date());
		order.setProviderLocation( new ProviderLocation(1L));
		order.getOrderItems().add(new OrderItem(1L, 8.99, new MenuItem(1L)));
		order.getOrderItems().add(new OrderItem(2L, 7.99, new MenuItem(2L)));
		orderService.save(order);
		
		Order order2 = orderService.get(order.getId());
		assertEquals(2, order2.getOrderItems().size());
	}
	
	@Test
	public void testGetOrdersSince() {
		User user = new User(2L);
		LocalDateTime startDate = LocalDateTime.now().minusDays(3);
		Collection<Order> orders = orderService.get(user, startDate);
		assertEquals(1, orders.size());
	}
}
