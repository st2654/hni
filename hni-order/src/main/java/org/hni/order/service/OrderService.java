package org.hni.order.service;

import java.time.LocalDate;
import java.util.Collection;

import org.hni.common.service.BaseService;
import org.hni.order.om.Order;
import org.hni.user.om.User;

public interface OrderService extends BaseService<Order> {

	Collection<Order> get(User user, LocalDate startDate);
	Collection<Order> get(User user, LocalDate startDate, LocalDate endDate);
}
