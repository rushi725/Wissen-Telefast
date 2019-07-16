package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CUSTOMERS")
public class Customer {
	public Customer( String firstName, String lastName, String address, String contactNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNo = contactNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int customerId;
	private String firstName;
	private String lastName;
	private String address;
	private String contactNo;

	public Customer() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return customerId;
	}

	public void setId(int id) {
		this.customerId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

}
