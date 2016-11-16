package org.hni.security.om;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hni.common.om.Persistable;

@Entity
@Table(name = "activation_codes")
public class ActivationCode implements Serializable, Persistable {

	private static final long serialVersionUID = -8813561563090879883L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activation_code")
	private String id;

	@Column(name = "organization_id") private Long organizationId;
	@Column(name = "created") private Date created;
	
	@Override
	public Object getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
