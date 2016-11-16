package org.hni.payment.service;

import javax.inject.Inject;

import org.hni.common.service.AbstractService;
import org.hni.payment.dao.OrderPaymentDAO;
import org.hni.payment.om.OrderPayment;
import org.hni.provider.dao.ProviderDAO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DefaultOrderPaymentService extends AbstractService<OrderPayment> implements OrderPaymentService {

	private OrderPaymentDAO orderPaymentDao;
	
	@Inject
	public DefaultOrderPaymentService(OrderPaymentDAO orderPaymentDao) {
		super(orderPaymentDao);
		this.orderPaymentDao = orderPaymentDao;
	}


}
