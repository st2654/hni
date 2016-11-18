package org.hni.payment.om;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hni.common.om.Persistable;
import org.hni.provider.om.Provider;

@Entity
@Table(name = "order_payments")
public class OrderPayment implements Serializable, Persistable {

	private static final long serialVersionUID = -1036619624568269599L;

	@EmbeddedId private OrderPaymentPK id;

	@Column(name="amount") private Double amount;
	@Column(name="created_by_id") private Long createdById;
	@Column(name="created_datetime") private Date createdDatetime;
	
	public OrderPayment() {}
	public OrderPayment(OrderPaymentPK id, Double amount, Long createdbyId, Date createdDatetime) {
		this.id = id;
		this.amount = amount;
		this.createdById = createdbyId;
		this.createdDatetime = createdDatetime;
	}

	public OrderPaymentPK getId() {
		return id;
	}

	public void setId(OrderPaymentPK id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	
	
}
