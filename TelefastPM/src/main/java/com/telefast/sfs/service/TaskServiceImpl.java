package com.telefast.sfs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	OrderedTaskRepository orderedTaskRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ServiceWorkflowRepository serviceWorkflowRepository;
	
	
	@Transactional
	@Override
	public Boolean transferTask(int taskId, int toEmployeeID) {
		OrderedTask orderedTask = orderedTaskRepository.findById(taskId).get();
		Employee emp =employeeRepository.findById(toEmployeeID).get(); 
		orderedTask.setEmployee(emp);
		return true;
	}

	@Transactional
	@Override
	public String assignTask(Task task,Team team,OrderedService orderedService) {
		int teamId= team.getId();
		List<Employee> employees = employeeRepository.findAllByTeamId(teamId); 
		for (Employee employee:employees) { 
			Boolean availStatus = employee.isAvailableStatus();
			if(availStatus) {
				OrderedTask orderedTask = new OrderedTask();
				orderedTask.setTask(task);
				orderedTask.setOrderedService(orderedService);
				orderedTask.setEmployee(employee);
				orderedTask.setTaskStatus(Status.NOT_STARTED);
				orderedTaskRepository.save(orderedTask);
				return "Task is assigned to "+employee.getFirstName()+" "+employee.getLastName();
			}
		}
		return "All employees of team are busy please try again";
	}
	@Transactional
	@Override
	public List<ServiceWorkflow> dependsOn(Task task, com.telefast.sfs.model.Service service, Team team) {
		int taskId=task.getId();
		int serviceId=service.getId();
		int teamId=team.getId();
		List<ServiceWorkflow> taskIds=serviceWorkflowRepository.findChildrenIds(taskId, serviceId);
		return taskIds;
	}
	
	
}
