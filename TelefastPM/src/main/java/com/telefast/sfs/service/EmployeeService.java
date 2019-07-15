package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.Team;

public interface EmployeeService {
	List<Employee> allAvailableEmployees(Team team);
}
