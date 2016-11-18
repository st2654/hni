package org.hni.payment.dao;

import org.hni.common.dao.AbstractDAO;
import org.hni.payment.om.OrderPayment;
import org.springframework.stereotype.Component;

@Component
public class DefaultOrderPaymentDAO extends AbstractDAO<OrderPayment> implements OrderPaymentDAO {

	protected DefaultOrderPaymentDAO() {
		super(OrderPayment.class);
	}

}
