package com.at.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Room implements Serializable {
	
	private static final long serialVersionUID = 6374272004167410735L;

	//@Id
	//@GeneratedValue
	//private Integer id;
	
	@Id
	private String location;
	
	@OneToMany
	private List<Shelf> shelves;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Shelf> getShelves() {
		return shelves;
	}

	public void setShelves(List<Shelf> shelves) {
		this.shelves = shelves;
	}
	
	
}
