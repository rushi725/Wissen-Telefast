package com.telefast.sfs.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.Team;

public interface EmployeeService {
	
	List<Employee> allAvailableEmployees(int teamId);
}
