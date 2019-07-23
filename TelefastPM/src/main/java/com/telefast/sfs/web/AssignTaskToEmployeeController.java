package com.telefast.sfs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.service.OrderedTaskService;
import com.telefast.sfs.service.TaskService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sfs/assignTask")
public class AssignTaskToEmployeeController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private OrderedTaskService orderedTaskService;

	//Assign Task to employee
	@PutMapping("/employee/{serviceId}/{taskId}/{orderedServiceId}")
	public ResponseEntity<?> assignTask(@PathVariable int serviceId,@PathVariable int taskId,@PathVariable int orderedServiceId){

		boolean response = taskService.assignTaskToEmployee(taskId,serviceId,orderedServiceId);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	//assign task to employeeId // transfer task to employee
	@PutMapping(value = "/{orderedTaskId}/{employeeId}")
	public ResponseEntity<?> transferTaskToEmployee(@PathVariable int orderedTaskId, @PathVariable int employeeId){
		
		boolean b =orderedTaskService.transferTask(orderedTaskId, employeeId);
		
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}


}
