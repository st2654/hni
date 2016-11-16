package org.hni.payment.service;

import java.util.Collection;

import javax.inject.Inject;

import org.hni.common.service.AbstractService;
import org.hni.payment.dao.PaymentInstrumentDAO;
import org.hni.payment.om.PaymentInstrument;
import org.hni.provider.om.Provider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DefaultPaymentInstrumentService extends AbstractService<PaymentInstrument> implements PaymentInstrumentService {

	private PaymentInstrumentDAO paymentInstrumentDao;
	
	@Inject
	public DefaultPaymentInstrumentService(PaymentInstrumentDAO paymentInstrumentDao) {
		super(paymentInstrumentDao);
		this.paymentInstrumentDao = paymentInstrumentDao;
	}

	@Override
	public Collection<PaymentInstrument> with(Provider provider, Double amount) {
		// TODO Auto-generated method stub
		return null;
	}


}
