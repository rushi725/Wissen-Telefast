package com.telefast.sfs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.TasksRepository;
import com.telefast.sfs.service.OrderedServiceService;
import com.telefast.sfs.service.OrderedTaskService;
import com.telefast.sfs.service.TaskService;
import com.telefast.sfs.service.WorkflowService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/startService")
public class StartService {

	@Autowired
	private WorkflowService workFlowService;
	
	@Autowired
	private OrderedTaskService orderedTaskService;
	
	@Autowired
	private OrderedServiceService orderedServiceService;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Autowired
	private TasksRepository taskRepository;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;
	
	
	
	
	@PutMapping("/{orderedServiceId}")
	public ResponseEntity<?> startService(@PathVariable int orderedServiceId, @RequestBody String s){
//		int count = workFlowService.getSequenceCount(serviceid);
		
		OrderedTask orderedTask = orderedTaskService.getFisrtOrderedTask(orderedServiceId);
		System.out.println(orderedTask.getTask().getName());
		int id = orderedTask.getOrderTaskId();
		System.out.println(id);
		
		OrderedService orderedService = orderedServiceRepository.findById(orderedServiceId).get();
		
		orderedService.setServiceStatus(Status.IN_PROGRESS);
		
		boolean response = taskService.assignOrderedTask(orderedTask);
		
		return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
			
	}
	
	@PutMapping("/startNext/{orderedTaskId}")
	public void startNextTasks(@PathVariable int orderedTaskId){
		
		OrderedTask completedTask = orderedTaskRepository.findById(orderedTaskId).get();
		
		orderedServiceService.updateProgress(completedTask.getOrderedService());
		
		System.out.println("inside starting next task---->");
				
		List<Task> childrenTasks = workFlowService.findChildrensByPrevTask(orderedTaskId);
		
		childrenTasks.forEach(e->System.out.println(e.getName()));

		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		OrderedService orderedService = orderedTask.getOrderedService();
		int orderedServiceId = orderedService.getOrderId();
		
		for(Task task:childrenTasks) {
			OrderedTask nextOrderedTask = orderedTaskService.findOrderedTaskByTask(task.getId(),orderedServiceId);
			taskService.assignOrderedTask(nextOrderedTask);
		}
		
	}

}
