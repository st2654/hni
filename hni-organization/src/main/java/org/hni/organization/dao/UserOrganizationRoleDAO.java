package org.hni.organization.dao;

import java.util.List;

import org.hni.common.dao.BaseDAO;
import org.hni.om.type.Role;
import org.hni.organization.om.Organization;
import org.hni.organization.om.UserOrganizationRole;

public interface UserOrganizationRoleDAO extends BaseDAO<UserOrganizationRole> {

	List<UserOrganizationRole> getByRole(Organization org, Role user);

}
