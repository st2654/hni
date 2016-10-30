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
	 * authenticate user by identifier (email address, mobile phone number), if
	 * matches then return token
	 * 
	 * @param user
	 * @return String token
	 */
	String authenticate(User user);

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
