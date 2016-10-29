package org.hni.user.service;

import java.util.List;

import org.hni.common.service.BaseService;
import org.hni.user.om.Permission;
import org.hni.user.om.RolePermission;

public interface RolePermissionService extends BaseService<RolePermission> {
	List<Permission> byRoleId(Long roleId);
}
