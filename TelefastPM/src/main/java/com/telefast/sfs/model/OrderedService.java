package com.telefast.sfs.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class OrderedService {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderId;
	private String installationAddress;
	@Enumerated
	private Status serviceStatus;
	private int progress;
	private LocalDateTime startDate;
	private LocalDateTime deliveryDate;
	private String serviceDenialReason;

	@OneToOne
	private Service service;

	@ManyToOne
	private Project project;

	@ManyToOne
	private Employee employee;

	public OrderedService() {
		// TODO Auto-generated constructor stub
	}

	public OrderedService(int orderId, String installationAddress, Status serviceStatus, int progress,
			LocalDateTime startDate, LocalDateTime deliveryDate, String serviceDenialReason) {
		this.orderId = orderId;
		this.installationAddress = installationAddress;
		this.serviceStatus = serviceStatus;
		this.progress = progress;
		this.startDate = startDate;
		this.deliveryDate = deliveryDate;
		this.serviceDenialReason = serviceDenialReason;
	}

}
