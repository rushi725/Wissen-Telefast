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
	private int taskId;
	private String name;
	private String description;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Boolean approvalNeeded;

	public Task() {
	}

	public int getId() {
		return taskId;
	}

	public void setId(int id) {
		this.taskId = id;
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Boolean getApprovalNeeded() {
		return approvalNeeded;
	}

	public void setApprovalNeeded(Boolean approvalNeeded) {
		this.approvalNeeded = approvalNeeded;
	}
	

}
