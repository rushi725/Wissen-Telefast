package com.telefast.sfs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.service.TaskService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sfs/assignTask")
public class AssignTaskToEmployeeController {
	
	@Autowired
	private TaskService taskService;
	
	//Assign Task to employee
	@PutMapping("/employee/{serviceId}/{taskId}/{orderedServiceId}")
	public ResponseEntity<?> assignTask(@PathVariable String serviceId,@PathVariable String taskId,@PathVariable String orderedServiceId){
	
		int taskId1 = Integer.parseInt(taskId);
		int serviceId1 = Integer.parseInt(serviceId);
		int orderedServiceId1 = Integer.parseInt(orderedServiceId);
		String response = taskService.assignTaskToEmployee(taskId1,serviceId1,orderedServiceId1);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	//Assign task to team
//	@PostMapping("/team/{serviceId}/{taskId}/{teamId}/{sequenceNo}")
//	public ResponseEntity<?> assignTask(){
//		
//	}


}
