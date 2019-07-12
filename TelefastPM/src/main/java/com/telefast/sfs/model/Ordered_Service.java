package com.telefast.sfs.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Ordered_Service {
	
	@Id
	@GeneratedValue
	private int orderId;
	
	
	private Service service;
	
//	@ManyToOne
//	private Project project;
	
	@ManyToOne
	private Employee serviceManagerId;
	private String installationAddress;
	private String serviceStatus;
	private String progress;
	private Date startDate;
	private Date deliveryDate;
	private String serviceDenialReason;
	
}
