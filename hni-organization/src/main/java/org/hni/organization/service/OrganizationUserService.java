package org.hni.organization.service;

import java.util.Collection;
import java.util.List;

import org.hni.om.Role;
import org.hni.organization.om.Organization;
import org.hni.organization.om.UserOrganizationRole;
import org.hni.user.om.User;
import org.hni.user.service.UserService;

public interface OrganizationUserService extends UserService {

	/**
	 * Adds a user to the specified organization with the given role
	 * @param user
	 * @param org
	 * @param role
	 * @return
	 */
	User save(User user, Organization org);

	/**
	 * Removes the user and all their data from the organization.
	 * Sets the DELETED flag on the User object.
	 * @param user
	 * @param org
	 * @return
	 */
	void delete(User user, Organization org);
	void delete(User user, Organization org, Role role);

	/**
	 * Archives the user in the given organization.  They are still associated with the org
	 * but all their normal roles are revoked and they are given the ARCHIVED role.
	 * @param user
	 * @param org
	 * @return
	 */
	User archive(User user, Organization org);
	
	/**
	 * Returns the users in the organization
	 * @param org
	 * @return
	 */
	Collection<User> getAllUsers(Organization org);

	/**
	 * Returns the set of users who play the given role in the organization
	 * @param org
	 * @param role
	 * @return
	 */
	Collection<User> getByRole(Organization org, Role role);

	/**
	 * Associates a user to an organization with the given role
	 * @param user
	 * @param org
	 * @param role
	 * @return 
	 */
	UserOrganizationRole associate(User user, Organization org, Role role);

	/**
	 * Returns a set of all the organizations a user belongs to.
	 * @param user
	 * @return
	 */
	Collection<Organization> get(User user);
	
}
