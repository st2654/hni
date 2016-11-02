package org.hni.security.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hni.common.service.AbstractService;
import org.hni.security.dao.UserTokenDAO;
import org.hni.security.om.UserToken;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DefaultUserTokenService extends AbstractService<UserToken> implements UserTokenService {
	private static final Logger logger = LoggerFactory.getLogger(UserTokenService.class);
	private UserTokenDAO userTokenDao;

	@Inject
	public DefaultUserTokenService(UserTokenDAO userTokenDao) {
		super(userTokenDao);
		this.userTokenDao = userTokenDao;
	}

	@Override
	public List<User> byToken(String token) {
		List<User> users = userTokenDao.byToken(token);
		for (User user : users) {
			user.setToken(token);
		}
		return users;
	}

	@Override
	public void deleteTokensOlderThan(Date date) {
		userTokenDao.deleteTokensOlderThan(date);
	}
}