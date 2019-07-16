package com.telefast.sfs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "SERVICEWORKFLOW")
public class ServiceWorkflow {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int workFlowId;
	@Column(nullable = true)
	private int seqNumber;
	private int nextTasks;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "taskId")
	private Task task;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "teamId")
	private Team team;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "serviceId")
	private Service service;

	public ServiceWorkflow() {

	}
	public ServiceWorkflow( int seqNumber, int nextTasks, Task task, Team team, Service service) {
		super();
		this.seqNumber = seqNumber;
		this.nextTasks = nextTasks;
		this.task = task;
		this.team = team;
		this.service = service;
	}

	public int getWorkflowId() {
		return workFlowId;
	}

	public void setWorkflowId(int workflowId) {
		this.workFlowId = workflowId;
	}

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}


	public int getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(int workFlowId) {
		this.workFlowId = workFlowId;
	}

	public int getNextTasks() {
		return nextTasks;
	}

	public void setNextTasks(int nextTasks) {
		this.nextTasks = nextTasks;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
}
