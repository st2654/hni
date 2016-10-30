package org.hni.security.service;

import java.util.Date;
import java.util.List;

import org.hni.common.service.BaseService;
import org.hni.security.om.UserToken;
import org.hni.user.om.User;

public interface UserTokenService extends BaseService<UserToken> {
	List<User> byToken(String token);

	void deleteTokensOlderThan(Date date);
}
