package org.hni.user.om.type;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gender {
	public static final Gender MALE = new Gender("M", "Male");
	public static final Gender FEMALE = new Gender("F", "Female");

	private String id;
	private String name;

	/*
	 * need default constructor for serialization / deserialization; defaulting
	 * to male.
	 */
	public Gender() {
		this.id = "M";
		this.name = "Male";
	}

	private Gender(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * Look up a UnitType by its id
	 * 
	 * @param id
	 * @return
	 */
	public static Gender get(String id) {
		for (Gender type : TYPES) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return null;
	}

	private static final Gender[] TYPES = { MALE, FEMALE };
	public static final List<Gender> VALUES = Collections.unmodifiableList(Arrays.asList(TYPES));

}
