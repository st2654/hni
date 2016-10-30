package org.hni.security.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hni.security.om.UserToken;
import org.hni.security.om.UserTokenPK;
import org.hni.user.om.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
@Transactional
public class TestUserTokenService {

	@Inject
	private UserTokenService userTokenService;

	@Test
	public void testGet() {
		UserTokenPK userTokenPk = new UserTokenPK();
		userTokenPk.setToken("ABCDEFGHIJKLMNOP");
		UserToken userToken = userTokenService.get(userTokenPk);
		assertTrue(userToken.getUserId().equals(1L));
	}

	@Test
	public void testInsert() {
		UserToken userToken = new UserToken(1L);
		String currentUserToken = userToken.getToken();
		UserToken updatedUserToken = userTokenService.insert(userToken);
		assertTrue(updatedUserToken.getToken().equals(currentUserToken));
	}

	@Test
	public void testDelete() {
		UserTokenPK userTokenPk = new UserTokenPK();
		userTokenPk.setToken("ABCDEFGHIJKLMNOP");
		UserToken userToken = userTokenService.get(userTokenPk);
		userTokenService.delete(userToken);
		UserToken deletedUserToken = userTokenService.get(userTokenPk);
		assertTrue(null == deletedUserToken);
	}

	@Test
	public void testByToken() {
		List<User> users = userTokenService.byToken("ABCDEFGHIJKLMNOP");
		assertTrue(null != users);
		assertTrue(!users.isEmpty());
		assertTrue(1 == users.size());
		User user = users.get(0);
		assertTrue("Super".equals(user.getFirstName()));
	}

	@Test
	public void testDeleteTokensOlderThanDate() {
		userTokenService.deleteTokensOlderThan(new Date());
		List<UserToken> tokens = userTokenService.getAll();
		assertTrue(null != tokens);
		assertTrue(tokens.isEmpty());
	}
}