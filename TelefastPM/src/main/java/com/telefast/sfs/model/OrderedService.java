package com.telefast.sfs.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class OrderedService {

	@Id
	@GeneratedValue
	private int orderId;
	private String installationAddress;
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
	private Employee serviceManagerId;
	
	public OrderedService() {
		// TODO Auto-generated constructor stub
	}

	
	
	
}
