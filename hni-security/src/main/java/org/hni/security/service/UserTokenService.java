package org.hni.security.service;

import java.util.Set;

import org.hni.security.om.AuthorizedUser;
import org.hni.security.om.OrganizationUserRolePermission;
import org.hni.user.om.User;

public interface UserTokenService {
	String getUserToke(User user, Long organizationId);

	AuthorizedUser getTokenUser(String token);

	Set<OrganizationUserRolePermission> getUserOrganizationRolePermissions(User user, Long organizationId);
}
