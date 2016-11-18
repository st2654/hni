package org.hni.security.om;

import java.util.Set;

import org.hni.user.om.User;

public class AuthorizedUser {

	public AuthorizedUser(User user) {
		this.setUser(user);
	}

	public AuthorizedUser() {

	}

	/**
	 * guid
	 */
	private static final long serialVersionUID = 2698586306174596113L;
	private User user;
	private Set<OrganizationUserRolePermission> permissions;
	private Long orgId = 0L;
	private Long tokenCreationTime;
	private Long tokenExpirationTime;

	public Set<OrganizationUserRolePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<OrganizationUserRolePermission> permissions) {
		this.permissions = permissions;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getTokenCreationTime() {
		return tokenCreationTime;
	}

	public void setTokenCreationTime(Long creationTime) {
		this.tokenCreationTime = creationTime;
	}

	public Long getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(Long expirationTime) {
		this.tokenExpirationTime = expirationTime;
	}
}
