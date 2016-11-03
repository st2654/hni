package org.hni.security.realm;

import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.hni.common.Constants;
import org.hni.user.dao.UserDAO;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(PasswordRealm.class);
	public static final String REALM_NAME = "passwordRealm";
	
	@Inject protected UserDAO userDao;
	
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) 
	{
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("Attempting authentication of user "+token.getPrincipal());
		// TODO Auto-generated method stub
		User user = null;
		
		try {
			user = userDao.byEmailAddress((String)token.getPrincipal());	
			if (null == user ) {
				logger.warn("Could not find User for principal:"+(String)token.getPrincipal());
				return new SimpleAuthenticationInfo("","", new SimpleByteSource(REALM_NAME.getBytes()), REALM_NAME);				
			}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new SimpleAuthenticationInfo("","", new SimpleByteSource(REALM_NAME.getBytes()), REALM_NAME);
		}
		ByteSource salt = new SimpleByteSource(Base64.decodeBase64(user.getSalt()));
		logger.info("Auth info = "+user.getEmail()+" - "+user.getHashedSecret());
		// if the user provided username/password then make sure there's no token as this messes up the AuthN
		if (!StringUtils.isBlank(user.getEmail()) && !StringUtils.isBlank(user.getHashedSecret())) {
			// remove the token from the session
			//Subject currentUser = SecurityUtils.getSubject();
			//Session session = currentUser.getSession();			
			//session.removeAttribute(Constants.CURRENT_USER_TOKEN);
		}
		return new SimpleAuthenticationInfo(user.getEmail(), user.getHashedSecret(), salt, REALM_NAME);

	}

}
