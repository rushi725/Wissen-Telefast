package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TEAMS")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String name;
	private String description;

	@OneToOne
	private Employee manager;

	public Team() {
	}

	public Team(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

}
