package com.telefast.sfs.web;

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
import com.telefast.sfs.model.Status;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.TasksRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sfs/orderedTask")
public class OrderedTaskController {
	
	@Autowired
	private TasksRepository tasksRepository;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;
	
	@GetMapping
	public List<OrderedTask> getOrderedTask(){
		return orderedTaskRepository.findAll();
	}
	
	@PostMapping
	public void add(@RequestBody OrderedTask orderedTask) {
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

		orderedTaskRepository.save(orderedTask);
	}
	//complete task by orderedTaskId
	@PutMapping("{orderedTaskId}/complete")
	public ResponseEntity<?> completeTask(@PathVariable String orderedTaskId){
		OrderedTask orderedTask = orderedTaskRepository.findTaskbyTaskId(Integer.parseInt(orderedTaskId));
		orderedTask.setTaskStatus(Status.COMPLETED);
		orderedTaskRepository.save(orderedTask);
		return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
	}
	
	//cancel task by orderedtaskId
	@PutMapping("{orderedTaskId}/cancel")
	public ResponseEntity<?> cancelTask(@RequestBody String reason, @PathVariable String orderedTaskId){
		OrderedTask orderedTask = orderedTaskRepository.findById(Integer.parseInt(orderedTaskId)).get();
		orderedTask.setTaskDenialReason(reason);
		orderedTask.setTaskStatus(Status.CANCELLED);
		orderedTaskRepository.save(orderedTask);
		return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
	}



}
