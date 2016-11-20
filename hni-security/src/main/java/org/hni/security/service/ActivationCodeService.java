package org.hni.security.service;

import org.hni.common.service.BaseService;
import org.hni.security.om.ActivationCode;
import org.hni.user.om.User;

public interface ActivationCodeService extends BaseService<ActivationCode> {

	/**
	 * Given an 6 digit authorization code that the customer received, returns
	 * the id of an AuthorizationCode
	 * @param authCode
	 * @return
	 */
	String decode(Long authCode);

	/**
	 * Given an id of AuthorizationCode, return a 6 digit number that the customer will use in
	 * order to register.
	 * @param id
	 * @return
	 */
	Long encode(String id);

	/**
	 * Given a 6 digit activation code, retrieve an ActivationCode entry from database
	 * @param authCode
	 * @return
	 */
	ActivationCode getByCode(Long authCode);

	/**
	 * Validate whether an activation code is valid or not
	 * @param id
	 * @return
	 */
	<T> boolean validate(T id);
}
