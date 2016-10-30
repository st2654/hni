package org.hni.security.service;

import java.util.List;

import org.hni.common.service.BaseService;
import org.hni.security.om.Permission;
import org.hni.security.om.RolePermission;

public interface RolePermissionService extends BaseService<RolePermission> {
	List<Permission> byRoleId(Long roleId);
}
