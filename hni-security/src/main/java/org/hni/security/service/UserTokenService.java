package org.hni.security.service;

import io.jsonwebtoken.Claims;

import java.util.Set;

import org.hni.security.om.OrganizationUserRolePermission;
import org.hni.user.om.User;

public interface UserTokenService {
	public static final String TOKEN_HEADER = "X-hni-token";

	// TODO: make these values dynamically injected
	public static final String KEY = "YbpWo521Z/aF7DqpiIpIHQ==";
	public static final String ISSUER = "test-issuer";

	/**
	 * retrieve claims from token
	 */
	Claims getClaimsFromToken(String token);

	/**
	 * retrieve user id from claims
	 */
	Long getUserIdFromClaims(Claims claims);

	/**
	 * retrieve organization id from claims
	 */
	Long getOrganizationIdFromClaims(Claims claims);

	/**
	 * retrieve permissions from claims
	 */
	Set<OrganizationUserRolePermission> getPermissionsFromClaims(Claims claims);

	/**
	 * retrieve permissions from token
	 */
	Set<OrganizationUserRolePermission> getPermissionsFromToken(String token);

	/**
	 * retrieve permissions based on user and organization id
	 */
	Set<OrganizationUserRolePermission> getUserOrganizationRolePermissions(User user, Long organizationId);

}
