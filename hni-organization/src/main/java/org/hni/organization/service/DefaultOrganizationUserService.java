package org.hni.organization.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.hni.om.type.Role;
import org.hni.organization.dao.UserOrganizationRoleDAO;
import org.hni.organization.om.Organization;
import org.hni.organization.om.UserOrganizationRole;
import org.hni.organization.om.UserOrganizationRolePK;
import org.hni.user.dao.UserDAO;
import org.hni.user.om.User;
import org.hni.user.service.DefaultUserService;
import org.springframework.stereotype.Component;

@Component("orgUserService")
public class DefaultOrganizationUserService extends DefaultUserService implements OrganizationUserService {

	private OrganizationService orgService;
	private UserOrganizationRoleDAO uorDao;
	@Inject
	public DefaultOrganizationUserService(UserDAO userDao, OrganizationService orgService, UserOrganizationRoleDAO uorDao) {
		super(userDao);
		this.orgService = orgService;
		this.uorDao = uorDao;
	}

	@Override
	public User save(User user, Organization org) {	
		super.save(user);
		
		UserOrganizationRole uor = new UserOrganizationRole(user, org, Role.USER);
		uorDao.save(uor);
		return user;
	}
	
	@Override
	public List<User> getAllUsers(Organization org) {
		Set<User> set = new HashSet<>();
		List<UserOrganizationRole> userRolesList = uorDao.getByRole(org, Role.USER);
		for(UserOrganizationRole uor : userRolesList) {
			set.add(super.get(((UserOrganizationRolePK)uor.getId()).getUserId()));
		}
		return new ArrayList<User>(set);
	}
	@Override
	public User delete(User user, Organization org) {
		// TODO
		return user;
	}

	@Override
	public User archive(User user, Organization org) {
		//TODO
		return user;
	}

}
