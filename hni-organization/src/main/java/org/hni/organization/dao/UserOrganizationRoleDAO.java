package org.hni.organization.dao;

import java.util.Collection;

import org.hni.common.dao.BaseDAO;
import org.hni.om.Role;
import org.hni.organization.om.Organization;
import org.hni.organization.om.UserOrganizationRole;
import org.hni.user.om.User;

public interface UserOrganizationRoleDAO extends BaseDAO<UserOrganizationRole> {

	Collection<UserOrganizationRole> getByRole(Organization org, Role user);
	Collection<UserOrganizationRole> get(User user);
}
