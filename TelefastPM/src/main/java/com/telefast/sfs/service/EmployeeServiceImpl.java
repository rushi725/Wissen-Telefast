package com.telefast.sfs.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Project;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.web.OrderedTaskRequest;

@Repository
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Transactional
	@Override
	public List<Employee> allAvailableEmployees(int teamId) {
		return employeeRepository.findAllAvailableByTeamId(teamId);
		}

	public List<OrderedTask> getAllInfoForEmployeeId(int employeeId) {
		List<OrderedTask> orderedTaskList = orderedTaskRepository.findByEmployeeId(employeeId);
		return orderedTaskList;
	}

	@Override
	public List<Employee> getAllEmployeesByTeam(int teamId) {
		List<Employee> list = employeeRepository.findAllByTeamId(teamId);
		return list;
	}


}
