package com.telefast.sfs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;
import com.telefast.sfs.repository.TasksRepository;

@Service
public class OrderedTaskServiceImpl implements OrderedTaskService{

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

	public boolean addOrderedTask(OrderedTask orderedTask) {
		Task task = new Task();
		OrderedService orderedService = new OrderedService();
		Employee teamMember = new Employee();
		System.out.println(orderedTask.getOrderedService().getOrderId());

		task = tasksRepository.findById(orderedTask.getTask().getId()).get();
		orderedService = orderedServiceRepository.findById(orderedTask.getOrderedService().getOrderId()).get();
		teamMember = employeeRepository.findById(orderedTask.getEmployee().getId()).get();

		System.out.println(orderedTask);

		orderedTask.setTask(task);
		orderedTask.setOrderedService(orderedService);
		orderedTask.setEmployee(teamMember);

		orderedTask = orderedTaskRepository.save(orderedTask);
		if(orderedTask == null)
			return false;
		else return true;

	}

	public boolean changeStatus(int orderedTaskId, int statusId) {
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();

		if (Status.values()[statusId] == Status.COMPLETED && orderedTask.getTask().getApprovalNeeded() == true) {
			orderedTask.setTaskStatus(Status.SENT_FOR_APPROVAL);
		} else {
			orderedTask.setTaskStatus(Status.values()[statusId]);
		}
		orderedTask.setTaskStatus(Status.values()[statusId]);
		
		orderedTask= orderedTaskRepository.save(orderedTask);
		
		if(orderedTask==null)
			return false;
		else return true;

	}
	
	public boolean approveTask(int orderedTaskId) {
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		orderedTask.setTaskStatus(Status.COMPLETED);

		orderedTask.setApproved(true);
		orderedTask =orderedTaskRepository.save(orderedTask);
		if(orderedTask == null)
			return false;
		else return true;

	}
	
	public boolean rejectTask(int orderedTaskId) {
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		orderedTask.setTaskStatus(Status.NOT_STARTED);
		orderedTask.setApproved(false);
		orderedTask = orderedTaskRepository.save(orderedTask);
		if(orderedTask == null)
			return false;
		else return true;
	}
	
	public boolean cancelTask(String reason, int orderedTaskId) {
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		orderedTask.setTaskDenialReason(reason);
		orderedTask.setTaskStatus(Status.CANCELLED);
		orderedTask.getEmployee().setAvailableStatus(true);
		orderedTask = orderedTaskRepository.save(orderedTask);

		if(orderedTask == null)
			return false;
		else return true;
	}
	
	@Override
	public boolean transferTask(int orderedTaskId, int toEmployeeId) {
		Employee toEmployee = employeeRepository.findById(toEmployeeId).get();
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		Employee fromEmployee = orderedTask.getEmployee();
		
		//set available status to true for fromEmployee
		fromEmployee.setAvailableStatus(true);
		
		//set available status to false for toEmployee
		orderedTask.setEmployee(toEmployee);
		toEmployee.setAvailableStatus(false);
				
		
		orderedTaskRepository.save(orderedTask);
		
		if(orderedTask.getEmployee().getId()==toEmployeeId)
		return true;
		
		else return false;
	}
	
	public List<OrderedTask> getOrderedTaskAssignedToTeamManager(int teamManagerId) {
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
