package org.hni.security.service;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.hni.common.Constants;
import org.hni.organization.om.UserOrganizationRole;
import org.hni.organization.service.OrganizationUserService;
import org.hni.security.om.OrganizationUserRolePermission;
import org.hni.security.om.Permission;
import org.hni.security.realm.token.JWTTokenFactory;
import org.hni.user.om.User;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DefaultUserTokenService implements UserTokenService {

	@Inject
	private OrganizationUserService orgUserService;
	@Inject
	private RolePermissionService rolePermissionService;

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public Claims getClaimsFromToken(String token) {
		return JWTTokenFactory.decode(token, KEY, ISSUER);
	}

	public Long getUserIdFromClaims(Claims claims) {
		Long userId = new Long(claims.get(Constants.USERID, Integer.class).longValue());
		return userId;
	}

	public Long getOrganizationIdFromClaims(Claims claims) {
		Long orgId = 0L;
		Set<OrganizationUserRolePermission> permissions = getPermissionsFromClaims(claims);
		for (OrganizationUserRolePermission permission : permissions) {
			orgId = permission.getOrganizationId();
			break;
		}
		return orgId;
	}

	public Set<OrganizationUserRolePermission> getPermissionsFromClaims(Claims claims) {
		Set<OrganizationUserRolePermission> permissions = new TreeSet<OrganizationUserRolePermission>();
		String permissionsString = claims.get(Constants.PERMISSIONS, String.class);
		try {
			List<OrganizationUserRolePermission> orgPermission = objectMapper.readValue(permissionsString,
					new TypeReference<List<OrganizationUserRolePermission>>() {
					});
			for (OrganizationUserRolePermission permission : orgPermission) {
				permissions.add(permission);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return permissions;
	}

	public Set<OrganizationUserRolePermission> getUserOrganizationRolePermissions(User user, Long organizationId) {
		Set<UserOrganizationRole> userOrgRoles = new TreeSet<UserOrganizationRole>();
		Collection<UserOrganizationRole> userOrganizationRoles = orgUserService.getUserOrganizationRoles(user);
		for (UserOrganizationRole userOrgRole : userOrganizationRoles) {
			if (organizationId.equals(userOrgRole.getId().getOrgId())) {
				userOrgRoles.add(userOrgRole);
			}
		}
		Set<OrganizationUserRolePermission> orgPermissions = new TreeSet<OrganizationUserRolePermission>();
		for (UserOrganizationRole userOrgRole : userOrgRoles) {
			OrganizationUserRolePermission orgUserRolePermission = new OrganizationUserRolePermission();
			orgUserRolePermission.setOrganizationId(organizationId);
			orgUserRolePermission.setRoleId(userOrgRole.getId().getRoleId());
			orgUserRolePermission.setUserId(user.getId());
			Set<Permission> permissions = rolePermissionService.byRoleId(userOrgRole.getId().getRoleId(), userOrgRole.getId().getOrgId());
			orgUserRolePermission.setPermissions(permissions);
			orgPermissions.add(orgUserRolePermission);
		}
		return orgPermissions;
	}

	@Override
	public Set<OrganizationUserRolePermission> getPermissionsFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return getPermissionsFromClaims(claims);
	}
}
