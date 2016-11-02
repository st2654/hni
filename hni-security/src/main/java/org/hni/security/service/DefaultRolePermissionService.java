package org.hni.security.service;

import java.util.List;

import javax.inject.Inject;

import org.hni.common.service.AbstractService;
import org.hni.security.dao.RolePermissionDAO;
import org.hni.security.om.Permission;
import org.hni.security.om.RolePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DefaultRolePermissionService extends AbstractService<RolePermission> implements RolePermissionService {
	private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);
	private RolePermissionDAO rolePermissionDao;

	@Inject
	public DefaultRolePermissionService(RolePermissionDAO rolePermissionDao) {
		super(rolePermissionDao);
		this.rolePermissionDao = rolePermissionDao;
	}

	@Override
	public List<Permission> byRoleId(Long roleId) {
		return rolePermissionDao.byRoleId(roleId);
	}

}