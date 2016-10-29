package org.hni.organization.service;

import java.util.Set;

import org.hni.user.om.OrganizationUserPermission;
import org.hni.user.om.User;

/**
 * Handle authentication, token validation, and authorization, as well as
 * providing the ability to clean up expired tokens.
 *
 */
public interface UserSecurityService {
	/**
	 * authenticate user, if matches then return token
	 * 
	 * @param userid
	 * @param password
	 * @return String token
	 */
	String authenticate(Long userid, String password);

	/**
	 * validate token, if valid return the user that requested it.
	 * 
	 * @param token
	 * @return User
	 */
	User validateToken(String token);

	/**
	 * authorize user based on token.
	 * 
	 * @param token
	 * @return Set<OrganizationUserPermission>
	 */
	Set<OrganizationUserPermission> authorize(String token);

	/**
	 * clean up tokens older than this many milliseconds
	 * 
	 * @param millisecondsBack
	 */
	void cleanupExpiredTokens(long millisecondsBack);
}
