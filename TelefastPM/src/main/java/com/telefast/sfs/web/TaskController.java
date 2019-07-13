package com.telefast.sfs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.TasksRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/tasks")
public class TaskController {
	
	private RestTemplate restTemplate= new RestTemplate();
	
	@Autowired
	private TasksRepository tasksRepository;
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;
	//-------------------------------------------------------------------
	//Team member
	//-------------------------------------------------------------------

	//get task by member
	@GetMapping(value = "/{employeeId}")
	public ResponseEntity<?> getTask(@PathVariable String employeeId) {
		Task  task = new Task();
		task=tasksRepository.findById(Integer.parseInt(employeeId)).get();
		
		OrderedTask orderedtask = new OrderedTask();
		orderedtask=orderedTaskRepository.findById(id))(Integer.parseInt(employeeId));
		
		ResponseEntity<Task> responseEntity = new ResponseEntity<Task>(task, HttpStatus.FOUND);
		return responseEntity;
	}
	
	
	//get all tasks assigned to a team
	@GetMapping(value = "/{teamManagerid}/tasks")
	public ResponseEntity<?> getTasks(@PathVariable String teamManagerId){
		return new ResponseEntity<>(orderedTaskRepository.find, HttpStatus.CREATED);
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> addTask(@RequestBody Task task){
		return new ResponseEntity<>(tasksRepository.save(task), HttpStatus.CREATED);
	}
	
	
//	
//	@GetMapping(value = "/{filter}")
//	public ResponseEntity<?> getTasksByFilter(@PathVariable String filter){
//		return new ResponseEntity<>(orderedTaskRepository.findbyStatus(filter), HttpStatus.FOUND);
//	}
	
	

}
