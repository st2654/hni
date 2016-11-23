package org.hni.security.realm.token;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.IncorrectClaimException;

public class TestTokenFactory {

	private static final String KEY = "YbpWo521Z/aF7DqpiIpIHQ==";
	private static final String ISSUER = "test-issuer";
	private static final Long TTL_MILLIS = 3600000L * 3; // 3 hrs
	
	@Test
	public void testValidateToken() {
		String subject = "testtoken";
		Long userId = 1L;
		String permissions = "test permissions";
		String token = JWTTokenFactory.encode(KEY, ISSUER, subject, TTL_MILLIS, userId, permissions);
		Claims claims = JWTTokenFactory.decode(token, KEY, ISSUER);
		assertNotNull(claims);
		assertEquals(permissions, claims.get("permissions", String.class));
		assertEquals(userId, new Long(claims.get("userId", Integer.class).longValue()));
		System.out.println(token);
	}

	@Test(expected=IncorrectClaimException.class)
	public void testInValidToken() {
		String subject = "testtoken";
		Long userId = 123L;
		String permissions = "test permissions";
		String token = JWTTokenFactory.encode(KEY, ISSUER+"-invalid", subject, TTL_MILLIS, userId, permissions);
		JWTTokenFactory.decode(token, KEY, ISSUER);
		fail();
	}

	@Test(expected=ExpiredJwtException.class)
	public void testExpiredToken() throws InterruptedException {
		String subject = "testtoken";
		Long ttlMillis = 1L;
		Long userId = 123L;
		String permissions = "test permissions";
		String token = JWTTokenFactory.encode(KEY, ISSUER, subject, ttlMillis, userId, permissions);
		Thread.sleep(100);
		JWTTokenFactory.decode(token, KEY, ISSUER);
		fail();
	}

}


