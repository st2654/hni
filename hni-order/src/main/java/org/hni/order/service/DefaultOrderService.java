package org.hni.order.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.inject.Inject;

import org.hni.common.service.AbstractService;
import org.hni.order.dao.OrderDAO;
import org.hni.order.om.Order;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DefaultOrderService extends AbstractService<Order> implements OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	private OrderDAO orderDao;
	
	@Inject
	public DefaultOrderService(OrderDAO orderDao) {
		super(orderDao);
		this.orderDao = orderDao;
	}

	@Override
	public Collection<Order> get(User user, LocalDate startDate) {
		return get(user, startDate, LocalDate.now());
	}

	@Override
	public Collection<Order> get(User user, LocalDate startDate, LocalDate endDate) {
		return this.orderDao.get(user,  startDate, endDate);
	}
}
