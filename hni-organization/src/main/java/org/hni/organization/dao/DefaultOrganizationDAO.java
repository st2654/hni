package org.hni.organization.dao;

import org.hni.common.dao.AbstractDAO;
import org.hni.organization.om.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultOrganizationDAO extends AbstractDAO<Organization> implements OrganizationDAO {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationDAO.class);
	
	public DefaultOrganizationDAO() {
		super(Organization.class);
	}

}
