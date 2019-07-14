package com.telefast.sfs.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class OrderedTask {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderedTaskId;
	
	@Enumerated
	private Status taskStatus;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String taskDenialReason;
	private Boolean approved;

	@OneToOne
	@JoinColumn(name = "taskId")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "orderedServiceId")
	private OrderedService orderedService;
	
	@OneToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	
	public int getOrderTaskId() {
		return orderedTaskId;
	}
	public void setOrderTaskId(int orderTaskId) {
		this.orderedTaskId = orderTaskId;
	}
	public Status getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
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
	public String getTaskDenialReason() {
		return taskDenialReason;
	}
	public void setTaskDenialReason(String taskDenialReason) {
		this.taskDenialReason = taskDenialReason;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public OrderedService getOrderedService() {
		return orderedService;
	}
	public void setOrderedService(OrderedService orderedService) {
		this.orderedService = orderedService;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	

}
