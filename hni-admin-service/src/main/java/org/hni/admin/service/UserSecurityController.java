package org.hni.admin.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hni.organization.service.OrganizationUserService;
import org.hni.security.om.OrganizationUserRolePermission;
import org.hni.security.om.Permission;
import org.hni.security.realm.token.JWTTokenFactory;
import org.hni.security.service.UserSecurityService;
import org.hni.security.service.UserTokenService;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Api(value = "/security", description = "Operations regarding authentication, authorization, and token validation.  All operations are POST, not due to updating RESTful entities, but because that puts the payload in the (hopefully) encrypted body, rather than as (plaintext) query parameters.")
@Component
@Path("/security")
public class UserSecurityController extends AbstractBaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);

	// TODO: make these values dynamically injected
	private static final String KEY = "YbpWo521Z/aF7DqpiIpIHQ==";
	private static final String ISSUER = "test-issuer";
	private static final Long TTL_MILLIS = 3600000L;
	@Inject
	private UserTokenService userTokenService;
	@Inject
	private OrganizationUserService organizationUserService;
	@Inject
	private UserSecurityService userSecurityService;

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/authentication")
	@ApiOperation(value = "Authenticates a user, returning a token for that user.", notes = "Requires username & password to be populated in the body", response = String.class, responseContainer = "")
	public String authenticate(UsernamePasswordToken userPasswordToken, Long organizationId) {
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(userPasswordToken);
			logger.info("Attempt to auth uyser " + userPasswordToken.getUsername());
			User user = organizationUserService.byEmailAddress(userPasswordToken.getUsername());
			logger.info("user is authenticated");
			Set<OrganizationUserRolePermission> permissions = userTokenService.getUserOrganizationRolePermissions(user, organizationId);
			String permissionObject = mapPermissionsToString(permissions);
			return JWTTokenFactory.encode(KEY, ISSUER, "", TTL_MILLIS, user.getId(), permissionObject);
		} catch (IncorrectCredentialsException ice) {
			logger.error("couldn't auth user:", ice.getMessage());
			return null;
		}
	}

	private String mapPermissionsToString(Set<OrganizationUserRolePermission> permissions) {
		String response = "";
		try {
			response = objectMapper.writeValueAsString(permissions);
		} catch (JsonProcessingException e) {
			logger.warn("Couldn't map permissions: ", e.getOriginalMessage());
		}
		return response;
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
	public Set<OrganizationUserRolePermission> authorize(String token) {
		return userSecurityService.authorize(token);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/loginrequired")
	@ApiOperation(value = "Sorry buddy, you have to auth first.", notes = "", response = Set.class, responseContainer = "")
	public String noaccess() {
		return "Authentication is required";
	}
}