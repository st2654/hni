package org.hni.user.dao;

import java.util.List;

import org.hni.common.dao.BaseDAO;
import org.hni.user.om.Permission;
import org.hni.user.om.RolePermission;

public interface RolePermissionDAO extends BaseDAO<RolePermission> {
	List<Permission> byRoleId(Long roleId);
}
