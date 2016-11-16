package org.hni.payment.dao;

import org.hni.common.dao.AbstractDAO;
import org.hni.payment.om.PaymentInstrument;
import org.springframework.stereotype.Component;

@Component
public class DefaultPaymentInstrumentDAO extends AbstractDAO<PaymentInstrument> implements PaymentInstrumentDAO {

	protected DefaultPaymentInstrumentDAO() {
		super(PaymentInstrument.class);
	}

}
