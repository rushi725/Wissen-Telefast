package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.web.OrderedTaskRequest;

public interface EmployeeService {
	

	List<Employee> allAvailableEmployees(int teamId);
		
	List<OrderedTask> getAllInfoForEmployeeId(int employeeId);

}
