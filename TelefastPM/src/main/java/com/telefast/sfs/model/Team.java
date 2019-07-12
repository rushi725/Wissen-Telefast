package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Team {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne
	private Employee manager;
	private String name;
	private String description;
	
	
	
	

}
