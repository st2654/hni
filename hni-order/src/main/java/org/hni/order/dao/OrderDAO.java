package org.hni.order.dao;

import java.time.LocalDateTime;
import java.util.Collection;

import org.hni.common.dao.BaseDAO;
import org.hni.order.om.Order;
import org.hni.user.om.User;

public interface OrderDAO extends BaseDAO<Order> {

	Collection<Order> get(User user, LocalDateTime startDate, LocalDateTime endDate);
}
