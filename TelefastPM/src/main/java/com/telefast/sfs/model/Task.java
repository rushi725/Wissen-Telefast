package com.telefast.sfs.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TASKS")
public class Task {
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", name=" + name + ", description=" + description + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", approvalNeeded=" + approvalNeeded + "]";
	}

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
	public Task( String name, String description, LocalDateTime startDate, LocalDateTime endDate,
			Boolean approvalNeeded) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.approvalNeeded = approvalNeeded;
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
