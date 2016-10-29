package org.hni.organization.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.hni.organization.om.UserOrganizationRole;
import org.hni.user.dao.DefaultUserTokenDAO;
import org.hni.user.dao.UserTokenDAO;
import org.hni.user.om.OrganizationUserPermission;
import org.hni.user.om.Permission;
import org.hni.user.om.User;
import org.hni.user.om.UserToken;
import org.hni.user.om.UserTokenPK;
import org.hni.user.service.RolePermissionService;
import org.hni.user.service.UserTokenService;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserSecurityService implements UserSecurityService {

	@Inject
	OrganizationUserService organizationUserService;

	@Inject
	UserTokenService userTokenService;

	@Inject
	RolePermissionService rolePermissionService;

	public String authenticate(Long userid, String password) {
		String tokenValue = "";
		/*
		 * TODO: known no salt.
		 * 
		 * TODO: will probably fail if no user with that userid
		 */
		User user = organizationUserService.get(userid);
		if (!user.getPassword().isEmpty()) {
			if (password.equals(user.getPassword())) {
				UserToken token = new UserToken(userid);
				tokenValue = token.getToken();
			}
		}
		return tokenValue;
	}

	public User validateToken(String token) {
		/* query for token in db. */
		User user = new User();
		UserTokenPK tokenPK = new UserTokenPK(token);
		UserToken userToken = userTokenService.get(tokenPK);
		/* If the token is still present, confirm its still valid */
		if (!userToken.getId().getToken().isEmpty()) {
			if (userToken.getCreated().getTime() < (System.currentTimeMillis() - 1000 * 60 * 30)) {
				/* remove the token if more than 30 minutes has passed. */
				userTokenService.delete(userToken);
			} else {
				/*
				 * TODO: may be better to just query the user by id (since we
				 * have it)
				 */
				List<User> users = userTokenService.byToken(token);
				if (users.size() > 0) {
					/* if token is still valid, return user. */
					user = users.get(0);
				}
			}
		}
		return user;
	}

	public Set<OrganizationUserPermission> authorize(String token) {
		Set<OrganizationUserPermission> organizationUserPermissions = new TreeSet<OrganizationUserPermission>();

		/* validate token */
		User user = validateToken(token);

		/* get organization roles for user */
		Collection<UserOrganizationRole> userOrganizationRoles = organizationUserService.getUserOrganizationRoles(user);

		/*
		 * get permissions per role (could be cached, not going to worry about
		 * that now)
		 */
		Set<Long> roleIds = new TreeSet<Long>();
		Long userId = 0L;
		Long organizationId = 0L;
		/*
		 * Minor optimization that can be achieved here by looking up each
		 * unique role permission set, rather than each per organization.
		 */
		for (UserOrganizationRole userOrgRole : userOrganizationRoles) {
			if (0L == userId) {
				userId = userOrgRole.getId().getUserId();
			}
			if (0L == organizationId) {
				organizationId = userOrgRole.getId().getOrgId();
			}

			Long roleId = userOrgRole.getId().getRoleId();
			roleIds.add(roleId);
		}
		for (Long roleId : roleIds) {
			List<Permission> permissions = rolePermissionService.byRoleId(roleId);
			/*
			 * for each permission in role, create an orgUserPermission object,
			 * and add it to the set.
			 */
			for (Permission permission : permissions) {
				OrganizationUserPermission orgUserPermission = new OrganizationUserPermission();
				orgUserPermission.setOrganizationId(organizationId);
				orgUserPermission.setUserId(userId);
				orgUserPermission.setPermissionId(permission.getId());
				organizationUserPermissions.add(orgUserPermission);
			}
		}
		return organizationUserPermissions;
	}

	public void cleanupExpiredTokens(long millisecondsBack) {
		/*
		 * remove from database all tokens older than milliseconds back from
		 * currentTime. This should be done asynchronously from another
		 * operation.
		 */
		UserTokenDAO userTokenDao = new DefaultUserTokenDAO();
		Long currentTime = System.currentTimeMillis();
		Long deleteOlderThan = currentTime - millisecondsBack;
		userTokenDao.deleteTokensOlderThan(new Date(deleteOlderThan));
	}
}
