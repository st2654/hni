package org.hni.security.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hni.common.dao.AbstractDAO;
import org.hni.security.om.UserToken;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserTokenDAO extends AbstractDAO<UserToken> implements UserTokenDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserTokenDAO.class);

	public DefaultUserTokenDAO() {
		super(UserToken.class);
	}

	@Override
	public List<User> byToken(String token) {
		try {
			Query q = em.createQuery("SELECT y FROM UserToken x, User y WHERE  x.userId = y.id AND x.id.token = :token").setParameter(
					"token", token);
			return q.getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public void deleteTokensOlderThan(Date date) {
		try {
			Query q = em.createQuery("DELETE FROM UserToken x WHERE x.created <= :created").setParameter("created", date);
			q.executeUpdate();
		} catch (NoResultException e) {

		}
	}
}
