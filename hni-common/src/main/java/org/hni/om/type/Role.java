package org.hni.om.type;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hni.om.Persistable;

//TODO: These roles should be in a table...

/**
 * Every user in the system must have a Role which determines the permissions that person
 * will have when/if they log into the system.
 * 
 * 
 * @author jeff3parker
 *
 */
public class Role implements Persistable, Serializable {
	private static final long serialVersionUID = -6502032699744741374L;

	public static final Role SUPER_USER = new Role(1L, "super-user", "Super User"); // everything
	public static final Role ADMIN = new Role(2L, "admin", "Administrator"); // everything for an org
	public static final Role USER = new Role(5L, "user", "User"); // basic stuff for an org
	public static final Role CUSTOMER = new Role(7L, "customer", "Customer"); // people we serve
	public static final Role TREASURER = new Role(9L, "treasurer", "Treasurer"); // handles money for an org
	
	public static final Role ARCHIVED = new Role(23L, "archived", "Archived"); // archived people
	
	private Long id;
	private String name;
	private String displayName;

	private Role(Long id, String name, String displayName) {
		this.id = id;
		this.name = name;
		this.displayName = displayName;
	}

	public Long getId() { return id; }
	public String getName() { return name; }
	public String getDisplayName() { return this.displayName; }

	/**
	 * Look up a Role by its id
	 * @param id
	 * @return
	*/
	public static Role get(Long id) {
		for(Role type : TYPES) {
			if ( type.getId().equals(id)) {
				return type;
			}
		}
		return new Role(id,"dynamic","dynamic");
	}
	
	private static final Role[] TYPES = { SUPER_USER, ADMIN, USER, TREASURER };
	public static final List<Role> VALUES = Collections.unmodifiableList(Arrays.asList(TYPES));

}
