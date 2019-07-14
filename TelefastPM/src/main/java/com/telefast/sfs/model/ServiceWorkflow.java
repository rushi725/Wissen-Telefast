package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "WORKFLOW")
public class ServiceWorkflow {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int workFlowId;
	private int seqNumber;
	
	@ManyToOne
	@JoinColumn(name = "taskId")
	private Task dependentOn;
//
//	@OneToOne
//	@JoinColumn(name = "taskId")
//	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "teamId")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name = "serviceId")
	private Service service;

	public ServiceWorkflow() {

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

	public Task getDependentOn() {
		return dependentOn;
	}

	public void setDependentOn(Task dependentOn) {
		this.dependentOn = dependentOn;
	}

//	public Task getTask() {
//		return task;
//	}
//
//	public void setTask(Task task) {
//		this.task = task;
//	}

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
