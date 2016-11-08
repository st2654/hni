package org.hni.order.service;

import org.apache.log4j.BasicConfigurator;
import org.hni.common.DateUtils;
import org.hni.order.om.Order;
import org.hni.provider.om.ProviderLocation;
import org.hni.user.om.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-applicationContext.xml"} )
@Transactional
public class TestOrderService {
	
	@Inject
	private OrderService orderService;
	
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

		// Checks that the pickup date is null prior to the completion methods
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

	@Test
	public void testNextOrder() {
		LocalDateTime now = LocalDateTime.now();
		//Loads 10 orders into database with two different providers and orders offset by 1 hour
		for (int i = 0; i < 5; i ++) {
			orderService.save(OrderTestData.getTestOrder(now.minus(i, ChronoUnit.MINUTES), new ProviderLocation(1L)));
			orderService.save(OrderTestData.getTestOrder(now.minus(i, ChronoUnit.MINUTES), new ProviderLocation(2L)));
		}

		Order order = orderService.next(new ProviderLocation(1L));

		assertTrue(order.getOrderDate().before(DateUtils.asDate(LocalDateTime.now())));
		assertEquals(new Long(1L) , order.getProviderLocation().getId());
	}
}
