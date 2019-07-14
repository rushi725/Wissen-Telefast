package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TEAMS")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int teamId;

	private String name;
	private String description;

	@OneToOne
	@JoinColumn(name = "employeeId")
	private Employee manager;

	public Team() {
	}

	public int getId() {
		return teamId;
	}

	public void setId(int id) {
		this.teamId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	

}
