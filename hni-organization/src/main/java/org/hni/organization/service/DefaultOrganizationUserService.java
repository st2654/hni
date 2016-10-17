package org.hni.organization.service;

import org.hni.om.type.Role;
import org.hni.organization.om.Organization;
import org.hni.user.dao.UserDAO;
import org.hni.user.om.User;
import org.hni.user.service.DefaultUserService;

public class DefaultOrganizationUserService extends DefaultUserService implements OrganizationUserService {

	public DefaultOrganizationUserService(UserDAO userDao) {
		super(userDao);
	}

	@Override
	public User save(User user, Organization org, Role role) {	
		//TODO: implement
		return user;
	}
	
	@Override
	public User delete(User user, Organization org) {
		//TODO
		return user;
	}

	@Override
	public User archive(User user, Organization org) {
		//TODO
		return user;
	}

}
