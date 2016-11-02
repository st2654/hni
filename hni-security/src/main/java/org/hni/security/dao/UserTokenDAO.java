package org.hni.security.dao;

import java.util.Date;
import java.util.List;

import org.hni.common.dao.BaseDAO;
import org.hni.security.om.UserToken;
import org.hni.user.om.User;


public interface UserTokenDAO extends BaseDAO<UserToken> {
	/**
	 * get user by token (should return only one user)
	 * 
	 * @param token
	 * @return List<User>
	 */
	List<User> byToken(String token);

	/**
	 * Token cleanup. This should be called with a constant of current date -
	 * however long we want the tokens to last. It should be run on a
	 * semi-regular basis (more frequently than the expiration time).
	 * 
	 * @param date
	 */
	void deleteTokensOlderThan(Date date);
}
