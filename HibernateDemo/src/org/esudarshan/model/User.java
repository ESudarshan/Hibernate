package org.esudarshan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "USER_INFO")
public class User {

	private int id;
	private String name;

	@Id
	@Column(name = "USER_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "USER_NAME")
	public String getName() {
		return name + " Modified in Getter";
	}

	public void setName(String name) {
		this.name = name;
	}

}
