package com.telefast.sfs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.service.TaskService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sfs/assignTask")
public class AssignTaskToEmployeeController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	//Assign Task to employee
	@PutMapping("/employee/{serviceId}/{taskId}/{orderedServiceId}")
	public ResponseEntity<?> assignTask(@PathVariable String serviceId,@PathVariable String taskId,@PathVariable String orderedServiceId){
	
		int taskId1 = Integer.parseInt(taskId);
		int serviceId1 = Integer.parseInt(serviceId);
		int orderedServiceId1 = Integer.parseInt(orderedServiceId);
		String response = taskService.assignTaskToEmployee(taskId1,serviceId1,orderedServiceId1);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	//assign task to employeeId // transfer task to employee
	@PutMapping(value = "/{orderedTaskId}/{employeeId}")
	public ResponseEntity<?> transferTaskToEmployee(@PathVariable int orderedTaskId, @PathVariable int employeeId){
		
		System.out.println("orderedTaskId--->"+orderedTaskId);
		System.out.println("employeeId--->"+employeeId);
		
		Employee toEmployee = employeeRepository.findById(employeeId).get();
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		Employee fromEmployee = orderedTask.getEmployee();
		
		//set available status to true for fromEmployee
		fromEmployee.setAvailableStatus(true);
		
		//set available status to false for toEmployee
		orderedTask.setEmployee(toEmployee);
		toEmployee.setAvailableStatus(false);
				
		
		orderedTaskRepository.save(orderedTask);
		
		return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
	}


}
