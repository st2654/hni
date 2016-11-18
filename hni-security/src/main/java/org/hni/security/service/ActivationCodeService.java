package org.hni.security.service;

import org.hni.common.service.BaseService;
import org.hni.security.om.ActivationCode;

public interface ActivationCodeService extends BaseService<ActivationCode> {

	Long decode(Long authCode);
	String encode(Long authCode);
	
	/**
	 * Validate whether an activation code is valid or not
	 * @param id
	 * @return
	 */
	boolean validate(String id);

}
