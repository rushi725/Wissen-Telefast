package com.telefast.sfs.model;

import javax.persistence.Entity;
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
	private String name;
	private String empRole;
	private String empAddress;
	private String empContactNo;
	private boolean availableStatus;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, Team team, String name, String empRole, String empAddress, String empContactNo,
			boolean availableStatus) {
		super();
		this.id = id;
		this.team = team;
		this.name = name;
		this.empRole = empRole;
		this.empAddress = empAddress;
		this.empContactNo = empContactNo;
		this.availableStatus = availableStatus;
	}

}
