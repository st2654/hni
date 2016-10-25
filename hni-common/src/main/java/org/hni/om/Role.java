package org.hni.om;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="providers")
public class Role implements Persistable, Serializable {

	private static final long serialVersionUID = -5344420286199389049L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name="name") private String name;
	
	public Role() {}
	public Role(Long id) {
		this.id = id;
	}
	public static Role get(Long id) {
		return new Role(id);
	}
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
