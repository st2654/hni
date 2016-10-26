package org.hni.provider.om;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hni.om.Persistable;
import org.hni.user.om.Role;
import org.hni.user.om.User;

/**
 * Represents an organization which could be an NGO, School or other entity providing
 * support and users to the system.
 * 
 * @author jeff3parker
 *
 */
@Entity
@Table(name = "user_provider_role")
public class UserProviderRole implements Persistable, Serializable {

	private static final long serialVersionUID = 2755700987714341746L;

	@EmbeddedId private UserProviderRolePK id;

	public UserProviderRole() {}
	public UserProviderRole(UserProviderRolePK id) {
		this.id = id;
	}
	public UserProviderRole(User user, Provider provider, Role role) {
		this(new UserProviderRolePK(user, provider, role));
	}
	@Override
	public Object getId() {
		return this.id;
	}

	public void setId(UserProviderRolePK id) {
		this.id = id;
	}
	
	
}
