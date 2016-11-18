package org.hni.security.om;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hni.common.om.Persistable;
import org.hni.user.om.User;

@Entity
@Table(name = "activation_codes")
public class ActivationCode implements Serializable, Persistable {

	private static final long serialVersionUID = -8813561563090879883L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activation_code")
	private String id;

	@Column(name = "organization_id") private Long organizationId;
	@Column(name = "meals_authorized") private Long mealsAuthorized;
	@Column(name = "meals_remaining") private Long mealsRemaining;
	@Column(name = "activated") private boolean activated;
	@Column(name = "comments") private String comments;
	@Column(name = "created") private Date created;
	
	// this is nullable
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;

	@Override
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getMealsAuthorized() {
		return mealsAuthorized;
	}

	public void setMealsAuthorized(Long mealsAuthorized) {
		this.mealsAuthorized = mealsAuthorized;
	}

	public Long getMealsRemaining() {
		return mealsRemaining;
	}

	public void setMealsRemaining(Long mealsRemaining) {
		this.mealsRemaining = mealsRemaining;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
