
package com.telefast.sfs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="EMPLOYEES")
public class Employee {

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", team=" + team.getId() + ", firstName=" + firstName + ", lastName="
				+ lastName + ", empRole=" + empRole + ", empAddress=" + empAddress + ", empContactNo=" + empContactNo
				+ ", availableStatus=" + availableStatus + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int employeeId;

	@ManyToOne(cascade = CascadeType.PERSIST)
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
	public Employee(Team team, String firstName, String lastName, EmpRole empRole, String empAddress,
			String empContactNo, boolean availableStatus) {
		super();
		this.team = team;
		this.firstName = firstName;
		this.lastName = lastName;
		this.empRole = empRole;
		this.empAddress = empAddress;
		this.empContactNo = empContactNo;
		this.availableStatus = availableStatus;
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
