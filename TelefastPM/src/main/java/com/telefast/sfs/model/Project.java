package com.telefast.sfs.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
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
	private Status status;
	private int progress;

	@OneToOne
	private Customer customer;
	@ManyToOne
	private Employee projectManager;

	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(int projectId, String name, LocalDateTime startDate, LocalDateTime deliveryDate, Status status,
			int progress) {
		super();
		this.projectId = projectId;
		this.name = name;
		this.startDate = startDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.progress = progress;
	}

}
