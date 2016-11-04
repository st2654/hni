package org.hni.admin.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class ValidTokenFilter extends AuthorizationFilter {

	private static final String TOKEN_HEADER = "X-hni-token";
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String tokenValue = httpRequest.getHeader(TOKEN_HEADER);
		// TODO: convert to token
		if (isValid(/*Token*/)) { 
			return true;
		}
		return false;
	}
	
	
	private boolean isValid(/*Token*/) {
		// TODO: pass in the token and validate it 
		return true;
	}

}
