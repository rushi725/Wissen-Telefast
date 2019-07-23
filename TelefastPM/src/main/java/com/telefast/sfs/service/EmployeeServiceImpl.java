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
		int id = teamId;
		return employeeRepository.findAllByTeamId(id);
		}

	public List<OrderedTaskRequest> getAllInfoForEmployeeId(int employeeId) {

		List<OrderedTask> orderedTaskList = orderedTaskRepository.findByEmployeeId(employeeId);
		List<OrderedTaskRequest> list = new ArrayList<OrderedTaskRequest>();

		for(OrderedTask orderedTask : orderedTaskList) {
			
		OrderedService orderedService = orderedServiceRepository.findById(orderedTask.getOrderedService().getOrderId()).get();
		System.out.println(orderedService.getService().getId());
		
		Project project = projectRepository.findById(orderedService.getProject().getProjectId()).get();
		System.out.println(project.getProjectId());
		
		Employee employee = employeeRepository.findById(employeeId).get();
		int teamId = employee.getTeam().getId();
		
		Employee manager = employeeRepository.getTeamManagerByTeamId(teamId);
		
		OrderedTaskRequest orderedTaskRequest = new OrderedTaskRequest();
		orderedTaskRequest.setOrderedService(orderedService);
		orderedTaskRequest.setOrderedTask(orderedTask);
		orderedTaskRequest.setProject(project);
		orderedTaskRequest.setEmployee(manager);
		
		list.add(orderedTaskRequest);
		}

		return list;
	}


}
