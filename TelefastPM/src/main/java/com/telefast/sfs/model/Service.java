package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Service {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	
	

}
