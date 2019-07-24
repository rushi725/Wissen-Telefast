package com.telefast.sfs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;
import com.telefast.sfs.repository.TasksRepository;

@Service
public class OrderedTaskServiceImpl implements OrderedTaskService {

	@Autowired
	private TasksRepository tasksRepository;

	@Autowired
	private OrderedServiceRepository orderedServiceRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private OrderedTaskRepository orderedTaskRepository;

	@Autowired
	private ServiceWorkflowRepository serviceWorkFlowRepository;

	@Autowired
	private WorkflowService workFlowService;

	public boolean addOrderedTask(OrderedTask orderedTask) {
		Task task = new Task();
		OrderedService orderedService = new OrderedService();
		Employee teamMember = new Employee();

		task = tasksRepository.findById(orderedTask.getTask().getId()).get();
		orderedService = orderedServiceRepository.findById(orderedTask.getOrderedService().getOrderId()).get();
		teamMember = employeeRepository.findById(orderedTask.getEmployee().getId()).get();


		orderedTask.setTask(task);
		orderedTask.setOrderedService(orderedService);
		orderedTask.setEmployee(teamMember);

		orderedTask = orderedTaskRepository.save(orderedTask);
		if (orderedTask == null)
			return false;
		else
			return true;

	}

	public boolean changeStatus(int orderedTaskId, int statusId) {
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		
		boolean flag=false;

		if (Status.values()[statusId] == Status.COMPLETED && orderedTask.getTask().getApprovalNeeded() == true) {
			orderedTask.setTaskStatus(Status.SENT_FOR_APPROVAL);
			flag=false;
		} else {
			if(Status.values()[statusId] == Status.COMPLETED) {
				orderedTask.getEmployee().setAvailableStatus(true);
				orderedTask.setEndDate(LocalDate.now());
				flag=true;
			}
			orderedTask.setTaskStatus(Status.values()[statusId]);
		}
		orderedTask = orderedTaskRepository.save(orderedTask);
		return flag;
	}

	public boolean approveTask(int orderedTaskId) {
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		orderedTask.setTaskStatus(Status.COMPLETED);
	    Employee employee = orderedTask.getEmployee();
	    employee.setAvailableStatus(true);
	    employeeRepository.save(employee);
		orderedTask.setEndDate(LocalDate.now());
		orderedTask.setApproved(true);
		orderedTask.getEmployee().setAvailableStatus(true);
		orderedTask = orderedTaskRepository.save(orderedTask);
		if (orderedTask == null)
			return false;
		else
			return true;
	}

	public boolean rejectTask(int orderedTaskId) {
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		orderedTask.setTaskStatus(Status.PENDING);
		orderedTask.setApproved(false);
		orderedTask = orderedTaskRepository.save(orderedTask);
		if (orderedTask == null)
			return false;
		else
			return true;
	}

	public boolean cancelTask(String reason, int orderedTaskId) {
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		orderedTask.setTaskDenialReason(reason);
		orderedTask.setTaskStatus(Status.CANCELLED);
		orderedTask.getEmployee().setAvailableStatus(true);
		orderedTask = orderedTaskRepository.save(orderedTask);

		if (orderedTask == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean transferTask(int orderedTaskId, int toEmployeeId) {
		Employee toEmployee = employeeRepository.findById(toEmployeeId).get();
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		Employee fromEmployee = orderedTask.getEmployee();

		// set available status to true for fromEmployee
		fromEmployee.setAvailableStatus(true);

		// set available status to false for toEmployee
		orderedTask.setEmployee(toEmployee);
		toEmployee.setAvailableStatus(false);

		orderedTaskRepository.save(orderedTask);

		if (orderedTask.getEmployee().getId() == toEmployeeId)
			return true;

		else
			return false;
	}

	public List<OrderedTask> getOrderedTaskAssignedToTeamManager(int teamManagerId) {
		System.out.println(teamManagerId);
		Team team = employeeRepository.getTeamByManagerId(teamManagerId);

		System.out.println("printing team id---->");
		System.out.println(team.getId());
		List<Task> tasks = serviceWorkFlowRepository.findTasksByTeamId(team.getId());

		List<Integer> taskIds = new ArrayList<Integer>();
		tasks.forEach(e -> taskIds.add(e.getId()));

		List<OrderedTask> orderedTaskList = orderedTaskRepository.findAllOrderedTaskByTaskId(taskIds);
		return orderedTaskList;
	}

	@Override
	public List<OrderedTask> getFisrtOrderedTask(int orderedServiceId) {
		
		OrderedService orderedService = orderedServiceRepository.findById(orderedServiceId).get();
		int serviceId = orderedService.getService().getId();

		List<Task> startingTasks = workFlowService.getFirstTask(serviceId);
		List<Integer> startingTaskIds = new ArrayList<Integer>();
		startingTasks.forEach(e -> startingTaskIds.add(e.getId()));

		List<OrderedTask> FirstOrderedTaskList = orderedTaskRepository.findAllOrderedTaskByTaskId(startingTaskIds);	
		
		FirstOrderedTaskList=FirstOrderedTaskList
		.stream()
		.filter(e->e.getOrderedService().getOrderId()==orderedServiceId)
		.collect(Collectors.toList());
		
		
		return FirstOrderedTaskList;
		

//		OrderedService orderedService = orderedServiceRepository.findById(orderedServiceId).get();
//		int serviceId = orderedService.getService().getId();
//
//		Task startingTask = workFlowService.getFirstTask(serviceId);
//		
//
//		OrderedTask FirstOrderedTask = orderedTaskRepository.findOrderTaskId(orderedServiceId, startingTask.getId());
//		
//		return FirstOrderedTask;
	}

	@Override
	public OrderedTask findOrderedTaskByTask(Integer taskId, int orderedServiceId) {
		
		OrderedTask orderedTask = orderedTaskRepository.findOrderTaskId(orderedServiceId, taskId);

//		List<OrderedTask> orderedTaskList = orderedTaskRepository.findOrderTaskId(orderedServiceId, taskId);

		return orderedTask;
	}

}
