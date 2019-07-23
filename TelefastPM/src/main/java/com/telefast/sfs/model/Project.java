package com.telefast.sfs.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "PROJECTS")
public class Project {
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", startDate=" + startDate + ", deliveryDate="
				+ deliveryDate + ", status=" + status + ", progress=" + progress + ", customer=" + customer.getId()
				+ ", projectManager=" + projectManager.getId() + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int projectId;
	private String name;
	private LocalDate startDate;
	private LocalDate deliveryDate;
	@Enumerated
	private Status status;
	private int progress;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name = "employeeId")
	private Employee projectManager;
	
	public Project(String name, LocalDate startDate, LocalDate deliveryDate, Status status,
			int progress, Customer customer, Employee projectManager) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.progress = progress;
		this.customer = customer;
		this.projectManager = projectManager;
	}

	public Project() {
		// TODO Auto-generated constructor stub
	}


	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Employee projectManager) {
		this.projectManager = projectManager;
	}

	
}
