package org.hni.order.om;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hni.om.Persistable;
import org.hni.provider.om.ProviderLocation;

@Entity
@Table(name = "orders")
public class Order implements Persistable, Serializable {

	private static final long serialVersionUID = 6662915064153704996L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name="order_date") private Date orderDate;
	@Column(name="ready_date") private Date readyDate;
	@Column(name="pickup_date") private Date pickupDate;
	@Column(name="subtotal") private Double subTotal;
	@Column(name="tax") private Double tax;
	@Column(name="created_by") private Long createdById;
	
	@ManyToOne
	@JoinColumn(name="provider_location_id", referencedColumnName = "id")
	private ProviderLocation providerLocation;

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getReadyDate() {
		return readyDate;
	}

	public void setReadyDate(Date readyDate) {
		this.readyDate = readyDate;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public ProviderLocation getProviderLocation() {
		return providerLocation;
	}

	public void setProviderLocation(ProviderLocation providerLocation) {
		this.providerLocation = providerLocation;
	}
	
	public Double getTotal() {
		return this.subTotal + this.tax;
	}
}
