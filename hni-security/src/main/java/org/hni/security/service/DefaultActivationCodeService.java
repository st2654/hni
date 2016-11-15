package org.hni.security.service;

import javax.inject.Inject;

import org.hni.common.service.AbstractService;
import org.hni.security.dao.ActivationCodeDAO;
import org.hni.security.om.ActivationCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DefaultActivationCodeService extends AbstractService<ActivationCode> implements ActivationCodeService {
	private static final Logger logger = LoggerFactory.getLogger(ActivationCodeService.class);
	private ActivationCodeDAO activationCodeDao;

	private static final int LARGE_BASE = 100000;
	private static final int LARGE_PRIME = 999983;
	private static final int OFFSET = 151647;
	
	@Inject
	public DefaultActivationCodeService(ActivationCodeDAO activationCodeDao) {
		super(activationCodeDao);
		this.activationCodeDao = activationCodeDao;
	}

	@Override
	public boolean validate(String id) {
		ActivationCode code = activationCodeDao.get(id);
		return (code == null);
	}
	
	@Override
	public String encode(Long authCode)
	{   long encodedCode;
		encodedCode=(305914*(authCode-LARGE_BASE)+OFFSET) % LARGE_PRIME; 
		return String.format("%06d", encodedCode);
	}
	
	@Override
	public Long decode(Long authCode )
	{   
		return (605673*(authCode -OFFSET)+LARGE_BASE) % LARGE_PRIME ;
 		
	}

}