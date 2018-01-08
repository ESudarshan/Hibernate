package org.esudarshan.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "USER_INFO")
public class User {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // (AUTO, IDENTITY, SEQUENCE, TABLE)
	private int id;

	private String name;

	@ElementCollection(fetch = FetchType.EAGER) // Default is Lazy Init
	@JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
	private Collection<Address> addresses = new ArrayList<>();

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

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return id + "\t" + name;
	}
}
