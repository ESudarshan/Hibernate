package org.esudarshan.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INFO")
public class User {

	// @EmbeddedId // Composite (Multi-column) Primary Key
	// private LoggedInUser loggedInUser;

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // (AUTO, IDENTITY, SEQUENCE, TABLE)
	private int id;

	private String name;

	@Embedded // Optional as class Address is declared as Embeddable
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "city", column = @Column(name = "OFFICE_CITY")),
			@AttributeOverride(name = "state", column = @Column(name = "OFFICE_STATE")) })
	private Address officeAddress;

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

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + homeAddress + "\t" + officeAddress;
	}
}
