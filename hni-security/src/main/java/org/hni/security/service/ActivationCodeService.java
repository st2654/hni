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
	Long decode(Long authCode);

	/**
	 * Given an id of AuthorizationCode, return a 6 digit number that the customer will use in
	 * order to register.
	 * @param id
	 * @return
	 */
	String encode(Long id);


	ActivationCode getByCode(Long authCode);

	/**
	 * Validate whether an activation code is valid or not
	 * @param id
	 * @return
	 */
	boolean validate(String id);

}
