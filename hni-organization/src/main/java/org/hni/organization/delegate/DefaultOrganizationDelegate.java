package org.hni.organization.delegate;

import javax.inject.Inject;

import org.hni.common.delegate.AbstractDelegate;
import org.hni.organization.dao.OrganizationDAO;
import org.hni.organization.om.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DefaultOrganizationDelegate extends AbstractDelegate<Organization> implements OrganizationDelegate {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationDelegate.class);
	private OrganizationDAO organizationDao;
	
	@Inject
	public DefaultOrganizationDelegate(OrganizationDAO organizationDao) {
		super(organizationDao);
		this.organizationDao = organizationDao;
	}
}
