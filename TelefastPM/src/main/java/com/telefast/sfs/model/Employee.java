package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@ManyToOne
	private Team team;
	private String firstName;
	private String lastName;
	@Enumerated
	private EmpRole empRole;
	private String empAddress;
	private String empContactNo;
	private boolean availableStatus;

	public Employee() {
		// TODO Auto-generated constructor stub
	}



}
