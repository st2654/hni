package org.hni.security.om;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Allows the system to associate any user to any organization with any role
 * 
 * @author jeff3parker
 *
 */
@Embeddable
public class UserTokenPK implements Serializable {

	/**
	 * guid
	 */
	private static final long serialVersionUID = -4089409658667366866L;
	@Column(name = "token")
	private String token;

	public UserTokenPK() {
	}

	public UserTokenPK(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return token.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return token.equals(obj);
	}
}
