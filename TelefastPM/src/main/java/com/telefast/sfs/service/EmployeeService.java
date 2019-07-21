package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.Employee;

public interface EmployeeService {
	

	List<Employee> allAvailableEmployees(int teamId);
}
