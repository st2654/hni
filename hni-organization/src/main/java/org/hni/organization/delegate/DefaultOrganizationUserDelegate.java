package org.hni.organization.delegate;

import org.hni.om.type.Role;
import org.hni.organization.om.Organization;
import org.hni.user.dao.UserDAO;
import org.hni.user.delegate.DefaultUserDelegate;
import org.hni.user.om.User;

public class DefaultOrganizationUserDelegate extends DefaultUserDelegate implements OrganizationUserDelegate {

	public DefaultOrganizationUserDelegate(UserDAO userDao) {
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
