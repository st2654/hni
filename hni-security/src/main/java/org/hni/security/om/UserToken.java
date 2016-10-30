package org.hni.security.om;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hni.common.om.Persistable;

/**
 * Represents a permission (ability for a user to perform a given action). These
 * are linked to users in organizations via roles.
 */
@Entity
@Table(name = "user_token")
public class UserToken implements Persistable, Serializable {
	private static final int TOKEN_LENGTH = 255;
	private static final long serialVersionUID = -5344420286199389049L;

	@EmbeddedId
	private UserTokenPK id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "created")
	private Date created;

	public UserToken() {
	}

	public UserToken(Long userId) {
		this.userId = userId;
		/*
		 * create a token.
		 * 
		 * TODO: may want to check to make sure a token with this value doesn't
		 * already exist for another user. This is a minimal probability.
		 */
		this.id = new UserTokenPK();
		this.id.setToken(org.apache.commons.lang3.RandomStringUtils.random(TOKEN_LENGTH));
		this.created = new Date();
	}

	public static UserToken get(Long id) {
		return new UserToken(id);
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public UserTokenPK getId() {
		return id;
	}

	public void setId(UserTokenPK id) {
		this.id = id;
	}

	public String getToken() {
		return getId().getToken();
	}
}