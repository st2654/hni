package org.hni.security.service.security;

import java.util.List;

import javax.inject.Inject;

import org.hni.organization.service.OrganizationUserService;
import org.hni.user.om.User;
import org.springframework.stereotype.Component;

@Component
public class MobilePhoneAuthenticator implements Authenticator {
	private OrganizationUserService organizationUserService;

	@Inject
	public MobilePhoneAuthenticator(OrganizationUserService organizationUserService) {
		this.organizationUserService = organizationUserService;
	}

	@Override
	public String authenticate(String mobilePhoneNumber, String password) {
		String tokenValue = "";
		List<User> users = organizationUserService.byMobilePhone(mobilePhoneNumber);
		User user = parseSingleUserFromList(users);
		if (validateUserPassword(user, password)) {
			tokenValue = generateSessionToken(user);
		}
		return tokenValue;
	}

}
