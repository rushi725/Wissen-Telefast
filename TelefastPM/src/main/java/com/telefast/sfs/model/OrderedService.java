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

import lombok.Data;

@Data
@Entity
public class OrderedService {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderedServiceId;
	private String installationAddress;
	@Enumerated
	private Status serviceStatus;
	private int progress;
	private LocalDateTime startDate;
	private LocalDateTime deliveryDate;
	private String serviceDenialReason;

	@OneToOne
	@JoinColumn(name = "serviceId")
	private Service service;

	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	public OrderedService() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderedServiceId;
	}

	public void setOrderId(int orderId) {
		this.orderedServiceId = orderId;
	}

	public String getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(String installationAddress) {
		this.installationAddress = installationAddress;
	}

	public Status getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(Status serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getServiceDenialReason() {
		return serviceDenialReason;
	}

	public void setServiceDenialReason(String serviceDenialReason) {
		this.serviceDenialReason = serviceDenialReason;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
