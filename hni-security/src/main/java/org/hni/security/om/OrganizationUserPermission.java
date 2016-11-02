package org.hni.security.om;

import java.io.Serializable;

public class OrganizationUserPermission implements Serializable, Comparable<OrganizationUserPermission> {
	/**
	 * guid
	 */
	private static final long serialVersionUID = -6914706948758886883L;
	private Long organizationId;
	private Long userId;
	private Long permissionId;
	private String permissionName;

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

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@Override
	public int compareTo(OrganizationUserPermission o) {

		if (0 == this.getOrganizationId().compareTo(o.getOrganizationId())) {
			if (0 == this.getUserId().compareTo(o.getUserId())) {
				return this.getPermissionId().compareTo(o.getPermissionId());
			} else {
				return this.getUserId().compareTo(o.getUserId());
			}
		} else {
			return this.getOrganizationId().compareTo(o.getOrganizationId());
		}
	}
}
