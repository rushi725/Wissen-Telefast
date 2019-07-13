package com.telefast.sfs.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERTASKS")
public class OrderedTask {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderTaskId;
	@Enumerated
	private Status taskStatus;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String taskDenialReason;
	private Boolean approved;

	@OneToOne
	private Task task;
	@ManyToOne
	private OrderedService orderedService;
	@OneToOne
	private Employee employee;

	public OrderedTask(int orderTaskId, Status taskStatus, LocalDateTime startDate, LocalDateTime endDate,
			String taskDenialReason, Boolean approved) {
		super();
		this.orderTaskId = orderTaskId;
		this.taskStatus = taskStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.taskDenialReason = taskDenialReason;
		this.approved = approved;
	}

}
