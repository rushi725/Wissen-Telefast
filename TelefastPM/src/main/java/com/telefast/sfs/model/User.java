package com.telefast.sfs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(name = "USERNAME")
	private String email;
	private Boolean active;
	private String password;

	@OneToOne
	private Employee employee;

	public User() {

	}

	public User(int id, String email, Boolean active, String password) {
		super();
		this.id = id;
		this.email = email;
		this.active = active;
		this.password = password;
	}

}
