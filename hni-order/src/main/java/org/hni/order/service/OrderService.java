package org.hni.order.service;

import java.time.LocalDate;
import java.util.Collection;

import org.hni.common.service.BaseService;
import org.hni.order.om.Order;
import org.hni.provider.om.Provider;
import org.hni.user.om.User;

public interface OrderService extends BaseService<Order> {

	/**
	 * Returns a collection of orders for the user created on the date.
	 * @param user
	 * @param startDate
	 * @return
	 */
	Collection<Order> get(User user, LocalDate startDate);
	
	/**
	 * Returns a collection of orders for the user between 2 dates
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Collection<Order> get(User user, LocalDate startDate, LocalDate endDate);
	
	/**
	 * Locks an order.  This is to prevent the system from giving the same order to another
	 * person to process.
	 * @param order
	 * @return
	 */
	Order lock(Order order);
	
	/**
	 * Releases the lock on an order.
	 * @param order
	 * @return
	 */
	Order release(Order order);
	
	/**
	 * Gets the next order to process where the order was created on a date
	 * @param orderDate
	 * @return
	 */
	Order next(LocalDate orderDate);
	
	/**
	 * Returns the next available order.
	 * @return
	 */
	Order next();
	
	/**
	 * Gets the next available order to process for a particular provider
	 * where the order was created on a date.
	 * @param provider
	 * @param orderDate
	 * @return
	 */
	Order next(Provider provider, LocalDate orderDate);
	
	/**
	 * Returns the next available order for a provider
	 * @param provider
	 * @return
	 */
	Order next(Provider provider);
	
	/**
	 * Sets an order to completed.
	 * @param order
	 * @return
	 */
	Order complete(Order order);
}
