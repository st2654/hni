package org.hni.organization.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.BasicConfigurator;
import org.hni.user.om.OrganizationUserPermission;
import org.hni.user.om.User;
import org.hni.user.om.UserToken;
import org.hni.user.service.UserTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
@Transactional
public class TestUserSecurityService {

	@Inject
	private UserSecurityService userSecurityService;

	@Inject
	private UserTokenService userTokenService;

	public TestUserSecurityService() {
		BasicConfigurator.configure();
	}

	@Test
	public void testAuthenticateUser() {
		String token = userSecurityService.authenticate(1L, "pwd");
		assertTrue(null != token);
		assertTrue(!token.isEmpty());
	}

	@Test
	public void testAuthorizeUser() {
		String tokenString = "ABCDEFGHIJKLMNOP";
		Set<OrganizationUserPermission> orgUserPermissions = userSecurityService.authorize(tokenString);
		assertTrue(null != orgUserPermissions);
		assertTrue(!orgUserPermissions.isEmpty());
		assertTrue(64 == orgUserPermissions.size());
		boolean createUserPermissionFound = false;
		for (OrganizationUserPermission orgUserPermission : orgUserPermissions) {
			if (5 == orgUserPermission.getPermissionId() && 2 == orgUserPermission.getOrganizationId()
					&& 1 == orgUserPermission.getUserId()) {
				createUserPermissionFound = true;
			}
		}
		assertTrue(createUserPermissionFound);
	}

	@Test
	public void testValidateToken() {
		String tokenString = "ABCDEFGHIJKLMNOP";
		User tokenUser = userSecurityService.validateToken(tokenString);
		assertTrue(1L == tokenUser.getId());
	}

	@Test
	public void testTokenCleanup() {
		List<UserToken> afterCleanupTokens = userTokenService.getAll();
		assertEquals(5, afterCleanupTokens.size());
	}
}