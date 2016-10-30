package org.hni.organization.service.security;

import java.util.List;

import javax.inject.Inject;

import org.hni.organization.service.OrganizationUserService;
import org.hni.user.om.User;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticator implements Authenticator {

	private OrganizationUserService organizationUserService;

	@Inject
	public EmailAuthenticator(OrganizationUserService organizationUserService) {
		this.organizationUserService = organizationUserService;
	}

	@Override
	public String authenticate(String emailAddress, String password) {
		String tokenValue = "";
		List<User> users = organizationUserService.byEmailAddress(emailAddress);
		User user = parseSingleUserFromList(users);
		if (validateUserPassword(user, password)) {
			tokenValue = generateSessionToken(user);
		}
		return tokenValue;
	}

}
