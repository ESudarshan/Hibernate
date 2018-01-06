package org.esudarshan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "USER_INFO")
public class User {

	@Id // Primary Key
	private int id;

	private String name;

	@Column(name = "JOINED_DATE")
	@Temporal(TemporalType.TIME) // (DATE, TIME OR TIMESTAMP)
	private Date joinedDate;

	@Transient
	private String address;

	@Lob // Large Object (BLOB or CLOB)
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + joinedDate + "\t" + address + "\t" + description;
	}
}
