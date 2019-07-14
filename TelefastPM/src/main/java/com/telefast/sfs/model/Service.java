package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int serviceId;
	private String name;
	private String description;

	public Service() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return serviceId;
	}

	public void setId(int id) {
		this.serviceId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
