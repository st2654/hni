package org.hni.admin.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hni.security.om.OrganizationUserPermission;
import org.hni.security.service.UserSecurityService;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Api(value = "/security", description = "Operations regarding authentication, authorization, and token validation.  All operations are POST, not due to updating RESTful entities, but because that puts the payload in the (hopefully) encrypted body, rather than as (plaintext) query parameters.")
@Component
@Path("/security")
public class UserSecurityController {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);

	@Inject
	private UserSecurityService userSecurityService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/authentication")
	@ApiOperation(value = "Authenticates a user, returning a token for that user.", notes = "Requires either (user phone # or email address) and password be populated in user", response = String.class, responseContainer = "")
	public User authenticate(User user) {
		return userSecurityService.authenticate(user);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/tokenValidation")
	@ApiOperation(value = "Validates a user's token, returning the user associated with the token if the token hasn't expired.  If the token has expired, will return an empty user.", notes = "Requires authentication token.", response = User.class, responseContainer = "")
	public User validateToken(String token) {
		return userSecurityService.validateToken(token);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/authorization")
	@ApiOperation(value = "Authorizes a user based on their token, returning a set of organization user permissions for that user and all organizations with which the user is associated .", notes = "Requires authentication token.  Returns Set<OrganizationUserPermission>", response = Set.class, responseContainer = "")
	public Set<OrganizationUserPermission> authorize(String token) {
		return userSecurityService.authorize(token);
	}
}