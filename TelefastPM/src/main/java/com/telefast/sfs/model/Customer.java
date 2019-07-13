package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CUSTOMERS")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String contactNo;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int id, String firstName, String lastName, String address, String contactNo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNo = contactNo;
	}

}
