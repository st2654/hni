package org.hni.payment.service;

import java.math.BigDecimal;
import java.util.Collection;

import org.hni.common.service.BaseService;
import org.hni.order.om.Order;
import org.hni.payment.om.OrderPayment;
import org.hni.provider.om.Provider;
import org.hni.user.om.User;

public interface OrderPaymentService extends BaseService<OrderPayment > {

	Collection<OrderPayment> paymentFor(Order order, Provider provider, Double amount, User user);

}
