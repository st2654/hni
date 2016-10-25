package org.hni.order.om;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hni.om.Persistable;
import org.hni.provider.om.MenuItem;

@Entity
@Table(name = "order_items")
public class OrderItem implements Persistable, Serializable {

	private static final long serialVersionUID = 1497593633752929478L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name="order_id") private Long order_id;
	@Column(name="quantity") private Long quantity;
	@Column(name="amount") private Double amount;

	@ManyToOne
	@JoinColumn(name="menu_item_id", referencedColumnName = "id")
	private MenuItem menuItem;

	@Override
	public Long getId() {
		return this.id;
	}

}
