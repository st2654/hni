package org.hni.order.service;

import java.time.LocalDateTime;
import java.util.Collection;

import org.hni.common.service.BaseService;
import org.hni.order.om.Order;
import org.hni.user.om.User;

public interface OrderService extends BaseService<Order> {

	Collection<Order> get(User user, LocalDateTime startDate);
	Collection<Order> get(User user, LocalDateTime startDate, LocalDateTime endDate);
}
