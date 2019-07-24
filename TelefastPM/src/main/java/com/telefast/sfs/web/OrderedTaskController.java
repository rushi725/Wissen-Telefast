package com.telefast.sfs.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;
import com.telefast.sfs.repository.TasksRepository;
import com.telefast.sfs.service.EmployeeService;
import com.telefast.sfs.service.OrderedTaskService;
import com.telefast.sfs.service.TaskService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sfs/orderedTask")
public class OrderedTaskController {

	@Autowired
	private OrderedTaskService orderedTaskService;

	@Autowired
	private OrderedTaskRepository orderedTaskRepository;

	@Autowired
	private ServiceWorkflowRepository serviceWorkFlowRepository;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;


	@GetMapping
	public List<OrderedTask> getOrderedTask() {
		return orderedTaskRepository.findAll();
	}
	
	@GetMapping("/{orderedServiceId}")
	public List<OrderedTask> getOrderedTaskByService(@PathVariable int orderedServiceId) {
		OrderedService orderedService = orderedServiceRepository.findById(orderedServiceId).get();
		return orderedTaskRepository.findByOrderedService(orderedService);
	}

	@PostMapping
	public void add(@RequestBody OrderedTask orderedTask) {
		orderedTaskService.addOrderedTask(orderedTask);
	}

	// get status list
	@GetMapping("/status")
	public ResponseEntity<?> getStatusList() {
		List<Status> status = Arrays.asList(Status.values());
		return new ResponseEntity<>(status, HttpStatus.ACCEPTED);
	}

	// change status of orderedTask by OrderedTaskId
	@PutMapping("/{orderedTaskId}/changeStatus/{statusId}")
	public ResponseEntity<?> changeStatus(@PathVariable int orderedTaskId, @PathVariable int statusId) {
		boolean flag = orderedTaskService.changeStatus(orderedTaskId, statusId);
		return new ResponseEntity<>(flag, HttpStatus.ACCEPTED);
	}

	// Approve task by orderedTaskId
	@PutMapping("/{orderedTaskId}/complete")
	public ResponseEntity<?> completeTask(@PathVariable int orderedTaskId) {
		boolean b = orderedTaskService.approveTask(orderedTaskId);
		
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}

	// Reject task by orderedTaskId
	@PutMapping("/{orderedTaskId}/reject")
	public ResponseEntity<?> rejectTask(@PathVariable int orderedTaskId) {
		boolean b =orderedTaskService.rejectTask(orderedTaskId);
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}

	// cancel task by orderedtaskId
	@PutMapping("{orderedTaskId}/cancel")
	public ResponseEntity<?> cancelTask(@RequestBody String reason, @PathVariable int orderedTaskId) {
		boolean b =orderedTaskService.cancelTask(reason, orderedTaskId);
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}

	// get all Info(OrderedTask, OrderedService, Project) assigned to Employee
	@GetMapping(value = "/employee/{employeeId}")
	public ResponseEntity<?> getTasksByEmployee(@PathVariable int employeeId) {
		
		List<OrderedTask> ordereTaskList = employeeService.getAllInfoForEmployeeId(employeeId);
				
		return new ResponseEntity<>(ordereTaskList, HttpStatus.ACCEPTED);
	}

	// get all OrderedTasks assigned to a team by teamManagerId
	@GetMapping(value = "/teamManager/{teamManagerId}")
	public ResponseEntity<?> getTasksByTeamManager(@PathVariable int teamManagerId) {
		System.out.println(teamManagerId);
		List<OrderedTask> orderedTaskList = orderedTaskService.getOrderedTaskAssignedToTeamManager(teamManagerId);
		return new ResponseEntity<>(orderedTaskList, HttpStatus.ACCEPTED);
	}

}
