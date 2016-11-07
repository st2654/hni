package org.hni.security.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hni.organization.service.OrganizationUserService;
import org.hni.security.dao.SecretDAO;
import org.hni.security.om.AuthorizedUser;
import org.hni.security.om.OrganizationUserRolePermission;
import org.hni.security.service.security.Authenticator;
import org.hni.security.service.security.EmailAuthenticator;
import org.hni.user.om.User;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserSecurityService implements UserSecurityService {
	private static final Log logger = LogFactory.getLog(UserSecurityService.class);

	@Inject
	private SecretDAO secretDAO;

	@Inject
	private OrganizationUserService organizationUserService;

	@Inject
	private RolePermissionService rolePermissionService;

	@Override
	public User authenticate(User user) {
		Authenticator authenticator = null;
		String identifier = null;
		String token = "";
		if (null != user) {
			if (null != user.getEmail() && !user.getEmail().isEmpty()) {
				authenticator = new EmailAuthenticator(organizationUserService, secretDAO, rolePermissionService);
				identifier = user.getEmail();
			}
		}
		token = authenticator.authenticate(identifier, user.getHashedSecret(), user.getOrganizationId());
		UserTokenService userTokenService = new DefaultUserTokenService(secretDAO, rolePermissionService, organizationUserService);
		AuthorizedUser authorizedUser = userTokenService.getTokenUser(token);
		User returnUser = authorizedUser.getUser();
		returnUser.setHashedSecret("");
		returnUser.setSalt("");
		returnUser.setToken(token);
		returnUser.setOrganizationId(user.getOrganizationId());
		return returnUser;
	}

	public User validateToken(String token) {
		UserTokenService userToken = new DefaultUserTokenService(secretDAO, rolePermissionService, organizationUserService);
		AuthorizedUser authorizedUser = userToken.getTokenUser(token);
		/*
		 * token validity confirmed in getTokenUser, if user is empty, token not
		 * valid or user not found. will return empty user in those cases.
		 */
		if (0 != authorizedUser.getOrgId()) {
			authorizedUser.getUser().setHashedSecret("");
			authorizedUser.getUser().setSalt("");
		}
		return authorizedUser.getUser();
	}

	public Set<OrganizationUserRolePermission> authorize(String token) {
		/*
		 * currently pulls permissions from the token (encrypted), so if they've
		 * had permissions change since they got their token, they won't show
		 * up.
		 */
		Set<OrganizationUserRolePermission> organizationUserPermissions = new HashSet<OrganizationUserRolePermission>();
		UserTokenService userToken = new DefaultUserTokenService(secretDAO, rolePermissionService, organizationUserService);
		AuthorizedUser authorizedUser = userToken.getTokenUser(token);
		if (0 != authorizedUser.getOrgId()) {
			organizationUserPermissions = authorizedUser.getPermissions();
		}
		return organizationUserPermissions;
	}
}