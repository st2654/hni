package org.hni.organization.dao;

import org.hni.common.dao.AbstractDAO;
import org.hni.organization.om.Organization;
import org.hni.organization.om.UserOrganizationRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserOrganizationRoleDAO extends AbstractDAO<UserOrganizationRole> implements UserOrganizationRoleDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserOrganizationRoleDAO.class);
	
	public DefaultUserOrganizationRoleDAO() {
		super(UserOrganizationRole.class);
	}

}
