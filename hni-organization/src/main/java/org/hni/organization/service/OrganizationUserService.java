package org.hni.organization.service;

import org.hni.om.type.Role;
import org.hni.organization.om.Organization;
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
	User save(User user, Organization org, Role role);

	/**
	 * Removes the user and all their data from the organization.
	 * Sets the DELETED flag on the User object.
	 * @param user
	 * @param org
	 * @return
	 */
	User delete(User user, Organization org);

	/**
	 * Archives the user in the given organization.  They are still associated with the org
	 * but all their normal roles are revoked and they are given the ARCHIVED role.
	 * @param user
	 * @param org
	 * @return
	 */
	User archive(User user, Organization org);

}
