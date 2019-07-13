package com.telefast.sfs.model;

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
@Table(name = "WORKFLOW")
public class ServiceWorkflow {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int workflowId;
	private int seqNumber;
	private Task dependentOn;

	@OneToOne
	private Task task;
	@ManyToOne
	private Team team;
	@ManyToOne
	private Service service;

	public ServiceWorkflow() {

	}

}
