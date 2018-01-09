package org.esudarshan.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INFO")
public class User {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // (AUTO, IDENTITY, SEQUENCE, TABLE)
	private int id;

	private String name;

	@OneToMany
	private Collection<Vehicle> vehicle = new ArrayList<>();

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

	public Collection<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Collection<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return id + "\t" + name;
	}
}
