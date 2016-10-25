package org.hni.admin.service;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hni.common.Constants;
import org.hni.om.Role;
import org.hni.organization.om.Organization;
import org.hni.organization.om.UserOrganizationRole;
import org.hni.organization.service.OrganizationUserService;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Path("/user")
public class UserServiceController {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);
	
	@Inject private OrganizationUserService orgUserService;
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public User getUser(@PathParam("id") Long id) {
		return orgUserService.get(id);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public User addOrSaveUser(User user) {
		return orgUserService.save(user);
	}

	@DELETE
	@Path("/{id}/org/{orgId}/role/{roleId}")
	@Produces({MediaType.APPLICATION_JSON})
	public String deleteUser(@PathParam("id") Long id, @PathParam("orgId") Long orgId, @PathParam("roleId") Long roleId) {
		User user = new User(id);
		Organization org = new Organization(orgId);
		orgUserService.delete(user, org, Role.get(roleId));
		return "OK";
	}

	@PUT
	@Path("/{id}/org/{orgId}/role/{roleId}")
	@Produces({MediaType.APPLICATION_JSON})
	public UserOrganizationRole addUserToOrg(@PathParam("id") Long id, @PathParam("orgId") Long orgId, @PathParam("roleId") Long roleId) {
		User user = new User(id);
		Organization org = new Organization(orgId);
		return orgUserService.associate(user, org, Role.get(roleId));
	}
	
	@GET
	@Path("/users/org/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Collection<User> getOrgUsers(@PathParam("id") Long id) {
		Organization org = new Organization(id);
		return orgUserService.getAllUsers(org);
	}
	
	@GET
	@Path("/clients/org/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Collection<User> getOrgClients(@PathParam("id") Long id) {
		Organization org = new Organization(id);
		return orgUserService.getByRole(org, Role.get(Constants.CLIENT));
	}
	
	
}
