package com.telefast.sfs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int employeeId;

	@ManyToOne
	@JoinColumn(name = "teamId")
	private Team team;
	private String firstName;
	private String lastName;
	@Enumerated
	private EmpRole empRole;
	private String empAddress;
	private String empContactNo;
	private boolean availableStatus;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return employeeId;
	}

	public void setId(int id) {
		this.employeeId = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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

	public EmpRole getEmpRole() {
		return empRole;
	}

	public void setEmpRole(EmpRole empRole) {
		this.empRole = empRole;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpContactNo() {
		return empContactNo;
	}

	public void setEmpContactNo(String empContactNo) {
		this.empContactNo = empContactNo;
	}

	public boolean isAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(boolean availableStatus) {
		this.availableStatus = availableStatus;
	}
	



}
