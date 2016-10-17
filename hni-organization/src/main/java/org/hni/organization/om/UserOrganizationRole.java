package org.hni.organization.om;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hni.om.IdProvider;

/**
 * Represents an organization which could be an NGO, School or other entity providing
 * support and users to the system.
 * 
 * @author jeff3parker
 *
 */
@Entity
@Table(name = "organizations")
public class UserOrganizationRole implements IdProvider, Serializable {

	private static final long serialVersionUID = 2755700987714341746L;

	@EmbeddedId UserOrganizationRolePK id;

	@Override
	public Object getId() {
		return this.id;
	}
}
