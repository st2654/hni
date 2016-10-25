package org.hni.provider.om;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hni.om.Persistable;

@Entity
@Table(name="provider_location_hours")
public class ProviderLocationHour implements Serializable, Persistable {

	private static final long serialVersionUID = 1037150455719358692L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	
	@Column(name="provider_location_id") private Long providerLocationId;
	@Column(name="dow") private String dow;
	@Column(name="open_hour_secs") private Long openHourSeconds;
	@Column(name="close_hour_secs") private Long closeHourSeconds;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getProviderLocationId() {
		return providerLocationId;
	}

	public void setProviderLocationId(Long providerLocationId) {
		this.providerLocationId = providerLocationId;
	}

	public String getDow() {
		return dow;
	}

	public void setDow(String dow) {
		this.dow = dow;
	}

	public Long getOpenHourSeconds() {
		return openHourSeconds;
	}

	public void setOpenHourSeconds(Long openHourSeconds) {
		this.openHourSeconds = openHourSeconds;
	}

	public Long getCloseHourSeconds() {
		return closeHourSeconds;
	}

	public void setCloseHourSeconds(Long closeHourSeconds) {
		this.closeHourSeconds = closeHourSeconds;
	}
}
