package org.hni.security.service.security;

import java.util.List;

import javax.inject.Inject;

import org.hni.organization.service.OrganizationUserService;
import org.hni.security.om.UserToken;
import org.hni.security.service.UserTokenService;
import org.hni.user.om.User;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticator implements Authenticator {

	private OrganizationUserService organizationUserService;
	private UserTokenService userTokenService;

	@Inject
	public EmailAuthenticator(OrganizationUserService organizationUserService, UserTokenService userTokenService) {
		this.organizationUserService = organizationUserService;
		this.userTokenService = userTokenService;
	}

	@Override
	public String authenticate(String emailAddress, String password) {
		String tokenValue = "";
		List<User> users = organizationUserService.byEmailAddress(emailAddress);
		User user = parseSingleUserFromList(users);
		if (validateUserPassword(user, password)) {
			UserToken userToken = generateSessionToken(user);
			userTokenService.insert(userToken);
			tokenValue = userToken.getToken();
		}
		return tokenValue;
	}
}