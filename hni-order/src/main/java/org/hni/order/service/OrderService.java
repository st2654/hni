package org.hni.order.service;

import java.util.Collection;
import java.util.Date;

import org.hni.common.service.BaseService;
import org.hni.order.om.Order;
import org.hni.user.om.User;

public interface OrderService extends BaseService<Order> {

	Collection<Order> get(User user, Date startDate);
	Collection<Order> get(User user, Date startDate, Date endDate);
}
