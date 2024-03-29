
package com.telefast.sfs.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
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
@Table(name="ORDEREDSERVICES")
public class OrderedService {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderedServiceId;

	private String installationAddress;
	@Enumerated
	private Status serviceStatus;
	private int progress;
	private LocalDate startDate;
	private LocalDate deliveryDate;
	private String serviceDenialReason;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "serviceId")
	private Service service;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "projectId")
	private Project project;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	public OrderedService() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderedService( String installationAddress, Status serviceStatus, int progress,
			LocalDate startDate, LocalDate deliveryDate, String serviceDenialReason, Service service,
			Project project, Employee employee) {
		super();
		this.installationAddress = installationAddress;
		this.serviceStatus = serviceStatus;
		this.progress = progress;
		this.startDate = startDate;
		this.deliveryDate = deliveryDate;
		this.serviceDenialReason = serviceDenialReason;
		this.service = service;
		this.project = project;
		this.employee = employee;
	}

	public int getOrderId() {
		return orderedServiceId;
	}

	@Override
	public String toString() {
		return "OrderedService [orderedServiceId=" + orderedServiceId + ", installationAddress=" + installationAddress
				+ ", serviceStatus=" + serviceStatus + ", progress=" + progress + ", startDate=" + startDate
				+ ", deliveryDate=" + deliveryDate + ", serviceDenialReason=" + serviceDenialReason + ", service="
				+ service.getId() + ", project=" + project.getProjectId() + ", employee=" + employee.getId() + "]";
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
