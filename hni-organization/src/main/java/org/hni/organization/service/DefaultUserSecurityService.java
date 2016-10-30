package org.hni.organization.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hni.organization.om.UserOrganizationRole;
import org.hni.organization.service.security.Authenticator;
import org.hni.organization.service.security.EmailAuthenticator;
import org.hni.organization.service.security.MobilePhoneAuthenticator;
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
	private static final Long INITIAL_CLEANUP_DELAY = 0L;
	private static final Long TOKEN_DURATION = 1000 * 60 * 30L;
	private static final Long TIME_BETWEEN_TOKEN_CLEANUP = 60 * 10L;
	private static final Log logger = LogFactory.getLog(UserSecurityService.class);
	private static final ScheduledExecutorService SCHEDULED_EXECUTOR = Executors.newScheduledThreadPool(1);

	private static boolean cleanupScheduleSet = false;

	public DefaultUserSecurityService() {
		if (!cleanupScheduleSet) {
			cleanupScheduleSet = true;
			SCHEDULED_EXECUTOR.scheduleAtFixedRate(() -> {
				cleanupExpiredTokens(TOKEN_DURATION);
			}, INITIAL_CLEANUP_DELAY, TIME_BETWEEN_TOKEN_CLEANUP, TimeUnit.SECONDS);
		}
	}

	@Override
	public String authenticate(User user) {
		Authenticator authenticator = null;
		String identifier = null;
		String token = "";
		if (null != user) {
			if (null != user.getEmail() && !user.getEmail().isEmpty()) {
				authenticator = new EmailAuthenticator(organizationUserService);
				identifier = user.getEmail();
			} else if (null != user.getMobilePhone() && !user.getMobilePhone().isEmpty()) {
				authenticator = new MobilePhoneAuthenticator(organizationUserService);
				identifier = user.getMobilePhone();
			}
		}
		token = authenticator.authenticate(identifier, user.getPassword());
		return token;
	}

	public User validateToken(String token) {
		/* query for token in db. */
		User user = new User();
		UserTokenPK tokenPK = new UserTokenPK(token);
		UserToken userToken = userTokenService.get(tokenPK);
		/* If the token is still present, confirm its still valid */
		if (!userToken.getId().getToken().isEmpty()) {
			if (userToken.getCreated().getTime() < (System.currentTimeMillis() - TOKEN_DURATION)) {
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
		Long deleteOlderThan = System.currentTimeMillis() - millisecondsBack;
		userTokenService.deleteTokensOlderThan(new Date(deleteOlderThan));
	}
}