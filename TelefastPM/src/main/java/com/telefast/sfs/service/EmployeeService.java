package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.web.OrderedTaskRequest;

public interface EmployeeService {
	

	List<Employee> allAvailableEmployees(int teamId);
	
//	OrderedTaskRequest getAllInfoForEmployeeId(int employeeId);
	
	List<OrderedTaskRequest> getAllInfoForEmployeeId(int employeeId);

}
