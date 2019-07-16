
package com.telefast.sfs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TEAMS")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int teamId;

	private String name;
	private String description;

	public Team() {
	}
	public Team( String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", name=" + name + ", description=" + description + "]";
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
