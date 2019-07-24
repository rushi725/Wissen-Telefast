package com.telefast.sfs.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;
import com.telefast.sfs.repository.TasksRepository;
import com.telefast.sfs.repository.TeamRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TasksRepository tasksRepository;

	@Autowired
	OrderedTaskRepository orderedTaskRepository;

	@Autowired
	OrderedServiceRepository orderedServiceRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ServiceWorkflowRepository serviceWorkflowRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ServiceWorkflowRepository serviceWorkFlowRepository;

//	@Transactional
//	@Override
//	public List<ServiceWorkflow> dependsOn(Task task, com.telefast.sfs.model.Service service, Team team) {
//		int taskId=task.getId();
//		int serviceId=service.getId();
//		int teamId=team.getId();
//		List<ServiceWorkflow> taskIds=serviceWorkflowRepository.findChildrenIds(taskId, serviceId);
//		return taskIds;
//	}

//	@Override
//	public boolean assignTaskToEmployee(int taskId, int serviceId, int orderedServiceId) {
//		// TODO Auto-generated method stub
////		ServiceWorkflow serviceWorkflow = serviceWorkflowRepository.getByTaskAndService(taskId, serviceId);
////		Team team = serviceWorkflow.getTeam();
////
////		OrderedTask orderedTask = orderedTaskRepository.findOrderTaskId(orderedServiceId, taskId);
////
////		List<Employee> employees = employeeRepository.findAllAvailableByTeamId(team.getId());
////		for (Employee employee : employees) {
////
////			orderedTask.setEmployee(employee);
////			employee.setAvailableStatus(false);
////			orderedTaskRepository.save(orderedTask);
////			return true;
////		}
//		return false;
//	}
//	@Override
//	public boolean assignTaskToEmployee(int taskId, int serviceId, int orderedServiceId) {
//		// TODO Auto-generated method stub
//		ServiceWorkflow serviceWorkflow = serviceWorkflowRepository.getByTaskAndService(taskId, serviceId);
//		Team team = serviceWorkflow.getTeam();
//		OrderedTask orderedTask = orderedTaskRepository.findOrderTaskId(orderedServiceId, taskId);
//		List<Employee> employees = employeeRepository.findAllByTeamId(team.getId());
//		int length = employees.size();
//		if (length == 0)
//			return false;
//		Random rand = new Random();
//		int n = rand.nextInt(length);
//		Employee employee = employees.get(n);
//		orderedTask.setEmployee(employee);
//		employee.setAvailableStatus(false);
//		orderedTaskRepository.save(orderedTask);
//		return true;
//
//	}

	@Override
	public boolean assignTaskToTeam(int serviceId, int taskId, int teamId, int sequenceNo) {

		com.telefast.sfs.model.Service service = serviceRepository.findById(serviceId).get();
		Task task = tasksRepository.findById(taskId).get();
		Team team = teamRepository.findById(teamId).get();

		ServiceWorkflow serviceWorkflow = new ServiceWorkflow();
		serviceWorkflow.setService(service);
		serviceWorkflow.setTask(task);
		serviceWorkflow.setTeam(team);
		serviceWorkflow.setSeqNumber(sequenceNo);

		serviceWorkflowRepository.save(serviceWorkflow);

		return true;
	}
//
//	@Override
//	public Task getTaskByEmployee(int employeeId) {
//		OrderedTask orderedtask = orderedTaskRepository.findByEmployeeId(employeeId);
//		int taskId = orderedtask.getTask().getId();
//		Task task = tasksRepository.findById(taskId).get();
//		return task;
//	}

	@Override
	public boolean assignOrderedTask(OrderedTask orderedTask) {

		int taskId = orderedTask.getTask().getId();

		OrderedService orderedService = orderedTask.getOrderedService();

		int serviceId = orderedService.getService().getId();

		ServiceWorkflow serviceWorkflow = serviceWorkflowRepository.getByTaskAndService(taskId, serviceId);
		Team team = serviceWorkflow.getTeam();

		List<Employee> employees = employeeRepository.findAllAvailableByTeamId(team.getId());
		
		
		int length = employees.size();
		if (length == 0)
			return false;
		Random rand = new Random();
		int n = rand.nextInt(length);
		Employee employee = employees.get(n);
		orderedTask.setEmployee(employee);
		employee.setAvailableStatus(false);
		orderedTaskRepository.save(orderedTask);
		return true;
		
		
//		for (Employee employee : employees) {
//
//			orderedTask.setEmployee(employee);
//			employee.setAvailableStatus(false);
//			orderedTask.setStartDate(LocalDate.now());
//			
//			orderedTaskRepository.save(orderedTask);
//			return true;
//		}
//		return false;

	}

}
