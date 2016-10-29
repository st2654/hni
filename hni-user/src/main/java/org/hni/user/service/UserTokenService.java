package org.hni.user.service;

import java.util.Date;
import java.util.List;

import org.hni.common.service.BaseService;
import org.hni.user.om.User;
import org.hni.user.om.UserToken;

public interface UserTokenService extends BaseService<UserToken> {
	List<User> byToken(String token);

	void deleteTokensOlderThan(Date date);
}
