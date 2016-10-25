package org.hni.provider.om;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hni.om.Persistable;

@Entity
@Table(name="menus")
public class Menu implements Persistable, Serializable {

	private static final long serialVersionUID = -441007028990038177L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	
	@Column(name="name") private String name;
	@Column(name="start_hour") private Long startHourAvailable;
	@Column(name="end_hour") private Long endHourAvailable;

	@OneToMany(cascade={CascadeType.ALL}, mappedBy="menu", fetch=FetchType.EAGER)
	private Set<MenuItem> menuItems = new HashSet<MenuItem>();

	@ManyToOne
	@JoinColumn(name="provider_id", referencedColumnName = "id")
	private Provider provider;
	
	@Override
	public Object getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStartHourAvailable() {
		return startHourAvailable;
	}

	public void setStartHourAvailable(Long startHourAvailable) {
		this.startHourAvailable = startHourAvailable;
	}

	public Long getEndHourAvailable() {
		return endHourAvailable;
	}

	public void setEndHourAvailable(Long endHourAvailable) {
		this.endHourAvailable = endHourAvailable;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Set<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	
}
