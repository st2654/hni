package org.hni.user.om;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hni.om.Persistable;
import org.hni.user.om.type.Gender;

/**
 * Represents any user defined in the system.  Users can play several different roles such as
 * Administrator, Treasurer, Customer, etc
 * 
 * Every User in the system MUST be associated to an Organization
 * 
 * @author jeff3parker
 *
 */
@Entity
@Table(name = "users")
public class User implements Persistable, Serializable {
	private static final long serialVersionUID = 7553475738921092329L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	
	@Column(name="firstName") private String firstName;
	@Column(name="lastName") private String lastName;
	@Column(name="genderCode") private String genderCode;
	@Column(name="mobilePhone") private String mobilePhone;
	@Column(name="email") private String email;
	@Column(name="deleted") private boolean deleted;
	
	public User() {}
	public User(String firstName, String lastName, String mobilePhone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobilePhone = mobilePhone;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGenderCode() {
		return this.genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public Gender getGender() {
		return Gender.get(this.genderCode);
	}
	public void setGender(Gender gender) {
		if (null != gender) {
			this.genderCode = gender.getId();
		}
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
