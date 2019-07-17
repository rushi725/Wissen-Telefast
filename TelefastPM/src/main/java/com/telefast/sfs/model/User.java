package com.telefast.sfs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "USERS")
public class User {
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", active=" + active + ", password=" + password
				+ ", employee=" + employee + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userId;
	@Column(name = "USERNAME")
	private String email;
	private Boolean active;
	private String password;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	public User() {

	}
	public User( String email, Boolean active, String password, Employee employee) {
		super();
		this.email = email;
		this.active = active;
		this.password = password;
		this.employee = employee;
	}

	public int getId() {
		return userId;
	}

	public void setId(int id) {
		this.userId = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
