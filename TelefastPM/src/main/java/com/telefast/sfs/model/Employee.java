package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Team team;
	private String name;
	private String empRole;
	private String empAddress;
	private String empContactNo;
	private boolean availableStatus;
	
	
	
	

}
