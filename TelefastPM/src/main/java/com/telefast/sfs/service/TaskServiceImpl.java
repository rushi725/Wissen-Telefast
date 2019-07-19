package com.telefast.sfs.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Project;
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
import com.telefast.sfs.web.OrderedTaskRequest;

@Service
public class TaskServiceImpl implements TaskService{
	
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
	public List<ServiceWorkflow> dependsOn(Task task, com.telefast.sfs.model.Service service, Team team) {
		int taskId=task.getId();
		int serviceId=service.getId();
		int teamId=team.getId();
		List<ServiceWorkflow> taskIds=serviceWorkflowRepository.findChildrenIds(taskId, serviceId);
		return taskIds;
	}

	@Override
	public String assignTaskToEmployee(int taskId, int serviceId, int orderedServiceId) {
		// TODO Auto-generated method stub
		ServiceWorkflow serviceWorkflow = serviceWorkflowRepository.getByTaskAndService(taskId, serviceId);
		Team team = serviceWorkflow.getTeam();
		
		OrderedTask orderedTask = orderedTaskRepository.findOrderTaskId(orderedServiceId, taskId);
		
		List<Employee> employees = employeeRepository.findAllByTeamId(team.getId());
		for(Employee employee:employees) {
			System.out.println("orderedTaskId-->"+orderedTask.getOrderTaskId());
			orderedTask.setEmployee(employee);
			System.out.println("----->"+orderedTask);
			orderedTaskRepository.save(orderedTask);
			System.out.println("Task Assigned to-->"+employee.getId());
			return "Task Assigned to-->"+employee.getId();
		}
		return "Not allocated";
	}

	@Override
	public boolean assignTaskToTeam(int serviceId, int taskId, int teamId,int sequenceNo) {
		
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

	@Override
	public OrderedTaskRequest getAllInfoForEmployeeId(int employeeId) {

		OrderedTask orderedTask = orderedTaskRepository.findByEmployeeId(employeeId);
		System.out.println(orderedTask.getTask().getId());

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
		
		return orderedTaskRequest;
	}

	@Override
	public List<OrderedTask> getOrderedTaskAssignedByTeamManager(int teamManagerId) {
		Employee teamManager = employeeRepository.getTeamByManagerId(teamManagerId);
		int teamId = teamManager.getTeam().getId();
		System.out.println("get teamManagerId");
		System.out.println(teamId);

		List<Task> tasks = serviceWorkFlowRepository.findTasksByTeamId(teamId);
		System.out.println("printing taskIds");

		List<Integer> taskIds = new ArrayList<Integer>();
		tasks.forEach(e -> taskIds.add(e.getId()));

		List<OrderedTask> orderedTaskList = orderedTaskRepository.findAllOrderedTaskByTaskId(taskIds);
		return orderedTaskList;
	}
	
	
}
