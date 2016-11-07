package org.hni.security.om;

import java.io.Serializable;
import java.util.Set;

public class OrganizationUserRolePermission implements Serializable {
	/**
	 * guid
	 */
	private static final long serialVersionUID = -6914706948758886883L;
	private Long organizationId;
	private Long userId;
	private Long roleId;
	private Set<Permission> permissions;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}
