package com.telefast.sfs.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TASKS")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private String description;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Boolean approvalNeeded;

	public Task() {

	}

	public Task(int id, String name, String description, LocalDateTime startDate, LocalDateTime endDate,
			Boolean approvalNeeded) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.approvalNeeded = approvalNeeded;
	}

	

}
