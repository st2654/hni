package org.hni.order.service;

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

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		Order order = OrderTestData.getTestOrder();

		orderService.save(order);
		
		Order order2 = orderService.get(order.getId());

		assertEquals(2, order2.getOrderItems().size());
	}
	
	@Test
	public void testGetOrdersSince() {
		User user = new User(2L);
		LocalDate startDate = LocalDate.now().minusDays(3);
		Collection<Order> orders = orderService.get(user, startDate);
		assertEquals(1, orders.size());
	}

	@Test
	public void testOrderComplete() {
		Order order = OrderTestData.getTestOrder();

		// Checks that the pickup date is null prior to the completion methos
		assertEquals(null, order.getPickupDate());

		Date pickupDate = new Date();
		Order returnOrder = orderService.complete(order);

		// Verifies that the pickup date has been updated
		assertTrue(returnOrder.getPickupDate().after(pickupDate));

		//Checks that it was properly loaded into database
		Order orderFromDatabase = orderService.get(order.getId());
		assertNotNull(orderFromDatabase);
		assertTrue(orderFromDatabase.getPickupDate().after(pickupDate));

	}
}
