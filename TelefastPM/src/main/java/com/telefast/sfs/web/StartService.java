package com.telefast.sfs.web;

import java.time.LocalDate;
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
import com.telefast.sfs.repository.OredredTaskTeamRepository;
import com.telefast.sfs.repository.ProjectRepository;
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
	private TasksRepository taskRepository;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	
	
	
	@PutMapping("/{orderedServiceId}")
	public ResponseEntity<?> startService(@PathVariable int orderedServiceId, @RequestBody String s){
		
		List<OrderedTask> orderedTaskList = orderedTaskService.getFisrtOrderedTask(orderedServiceId);
		
		OrderedService orderedService = orderedServiceRepository.findById(orderedServiceId).get();
		
		System.out.println("printing ordered tasks ordereddService-->"+orderedServiceId);
		orderedTaskList.forEach(e -> System.out.println(e.getTask().getId()));
		
		orderedTaskList.forEach(e->taskService.assignOrderedTask(e));
//		boolean response = taskService.assignOrderedTask(orderedTask);
		
		orderedService.setServiceStatus(Status.IN_PROGRESS);
		orderedService.getProject().setStatus(Status.IN_PROGRESS);
		orderedServiceRepository.save(orderedService);

		
		
		return new ResponseEntity<>(orderedServiceRepository.save(orderedService), HttpStatus.ACCEPTED);
			
	}
	
	@PutMapping("/startNext/{orderedTaskId}")
	public void startNextTasks(@PathVariable int orderedTaskId){
		
		OrderedTask completedTask = orderedTaskRepository.findById(orderedTaskId).get();
		completedTask.setEndDate(LocalDate.now());
		orderedTaskRepository.save(completedTask);
				
		List<Task> childrenTasks = workFlowService.findChildrensByPrevTask(orderedTaskId);
		
		System.out.println("printing children task ids of-->"+orderedTaskId);
		
		for(Task task:childrenTasks) {
			System.out.println(task.getId());
		}
		
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		OrderedService orderedService = orderedTask.getOrderedService();
		int orderedServiceId = orderedService.getOrderId();
		
		for(Task task:childrenTasks) {
			OrderedTask nextOrderedTask = orderedTaskService.findOrderedTaskByTask(task.getId(),orderedServiceId);
			taskService.assignOrderedTask(nextOrderedTask);
		}
		
		orderedServiceService.updateProgress(completedTask.getOrderedService());
		orderedServiceService.updateProjectProgress(completedTask.getOrderedService().getProject());
		
	}

}
