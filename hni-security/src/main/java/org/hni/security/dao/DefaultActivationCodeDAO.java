package org.hni.security.dao;

import org.hni.common.dao.AbstractDAO;
import org.hni.security.om.ActivationCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultActivationCodeDAO extends AbstractDAO<ActivationCode> implements ActivationCodeDAO {
	private static final Logger logger = LoggerFactory.getLogger(ActivationCodeDAO.class);
	
	public DefaultActivationCodeDAO() {
		super(ActivationCode.class);		
	}
	
	
}
