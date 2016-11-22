package org.hni.payment.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.inject.Inject;

import org.hni.common.service.AbstractService;
import org.hni.order.om.Order;
import org.hni.order.service.LockingService;
import org.hni.payment.dao.OrderPaymentDAO;
import org.hni.payment.dao.PaymentInstrumentDAO;
import org.hni.payment.om.OrderPayment;
import org.hni.payment.om.PaymentInstrument;
import org.hni.provider.om.Provider;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DefaultOrderPaymentService extends AbstractService<OrderPayment> implements OrderPaymentService {
	private static final Logger logger = LoggerFactory.getLogger(DefaultOrderPaymentService.class);
	private static final Long FIVE_MINUTES = 5L;
	
	private OrderPaymentDAO orderPaymentDao;
	private PaymentInstrumentDAO paymentInstrumentDao;
	private LockingService lockingService;
	
	@Inject
	public DefaultOrderPaymentService(OrderPaymentDAO orderPaymentDao, PaymentInstrumentDAO paymentInstrumentDao, LockingService lockingService) {
		super(orderPaymentDao);
		this.orderPaymentDao = orderPaymentDao;
		this.lockingService = lockingService;
	}

	/**
	 * Return CARD, amount to use on this card
	 */
	@Override
	public Collection<OrderPayment> paymentFor(Order order, Provider provider, Double amount, User user) {
		Collection<PaymentInstrument> providerCards = paymentInstrumentDao.with(provider);
		Collection<OrderPayment> orderPayments = new HashSet();
		
		BigDecimal amountDispensed = BigDecimal.ZERO;
		// this is horribly crude, but will work for v1
		for(PaymentInstrument paymentInstrument : providerCards) {
			if (lockingService.acquireLock(lockingKey(paymentInstrument), FIVE_MINUTES)) {
				logger.info("locking card "+lockingKey(paymentInstrument));
				Double amountToDispense = calcAmountToDispense(paymentInstrument, amount);
				amountDispensed.add(BigDecimal.valueOf(amountToDispense));
				BigDecimal cardTotal = BigDecimal.valueOf(paymentInstrument.getBalance()).subtract(BigDecimal.valueOf(amountToDispense));
				
				OrderPayment orderPayment = new OrderPayment(order, paymentInstrument, amountToDispense, user);
				orderPayments.add(orderPayment);
				orderPaymentDao.save(orderPayment);
				
				paymentInstrument.setBalance(cardTotal.doubleValue());
				paymentInstrument.setLastUsedDatetime(new Date());
				paymentInstrumentDao.save(paymentInstrument);
				
				if (amountDispensed.doubleValue() >= amount) {
					break;
				}
			}
		}
		return orderPayments;
	}
	
	private Double calcAmountToDispense(PaymentInstrument paymentInstrument, Double amount) {
		if (paymentInstrument.getBalance().doubleValue() >= amount.doubleValue()) {
			return amount;
		}
		// the amount on the card is less than we need, so return the full amount on the card.
		return paymentInstrument.getBalance();		
	}
	
	private String lockingKey(PaymentInstrument paymentInstrument) {
		return String.format("paymentInstrument:%d", paymentInstrument.getId());
	}

}
