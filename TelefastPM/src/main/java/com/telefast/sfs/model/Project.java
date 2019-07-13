package com.telefast.sfs.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PROJECTS")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int projectId;
	private String name;
	private LocalDateTime startDate;
	private LocalDateTime deliveryDate;
	@Enumerated
	private Status status;
	private int progress;

	@OneToOne
	private Customer customer;
	@OneToOne
	private Employee projectManager;

	public Project() {
		// TODO Auto-generated constructor stub
	}

	
}
