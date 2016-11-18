package org.hni.payment.om;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderPaymentPK implements Serializable {

	private static final long serialVersionUID = 2751060551106019525L;
	
	@Column(name="order_id") private Long orderId;
	@Column(name="payment_instrument_id") private Long paymentInstrumentId;

	public OrderPaymentPK() {}
	public OrderPaymentPK(Long orderId, Long paymentInstrumentId) {
		this.orderId = orderId;
		this.paymentInstrumentId = paymentInstrumentId;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getPaymentInstrumentId() {
		return paymentInstrumentId;
	}
	public void setPaymentInstrumentId(Long paymentInstrumentId) {
		this.paymentInstrumentId = paymentInstrumentId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((paymentInstrumentId == null) ? 0 : paymentInstrumentId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderPaymentPK other = (OrderPaymentPK) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (paymentInstrumentId == null) {
			if (other.paymentInstrumentId != null)
				return false;
		} else if (!paymentInstrumentId.equals(other.paymentInstrumentId))
			return false;
		return true;
	}
	
	
	
}
