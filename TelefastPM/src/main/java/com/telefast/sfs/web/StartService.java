package com.telefast.sfs.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.TasksRepository;
import com.telefast.sfs.service.WorkflowService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/startService")
public class StartService {

	@Autowired
	private WorkflowService workFlowService;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Autowired
	private TasksRepository taskRepository;
	
	@PutMapping("/{serviceId}")
	public ResponseEntity<?> startService(@PathVariable int serviceId){
//		int count = workFlowService.getSequenceCount(serviceid);
		
		Task startingTask = workFlowService.getFirstTask(serviceId);
		
		System.out.println("starting Task--->");
		System.out.println(startingTask.getId());
		System.out.println(serviceId);
		List<Integer> list = workFlowService.findChildrenIds(startingTask.getId(),serviceId);

		
			
			list.forEach(taskId->{
				
				System.out.println("----"+taskId);
								
				Task task = taskRepository.findById(taskId).get();

				OrderedTask orderedTask = new OrderedTask();
				System.out.println("a");

				OrderedService orderedService = orderedServiceRepository.findByServiceId(serviceId);
				System.out.println("b");

				orderedTask.setTask(task);
				System.out.println("c");

				orderedTask.setOrderedService(orderedService);
				System.out.println("d");

//				Employee employee = new Employee();
//				
//				orderedService.setEmployee(employee);
//				System.out.println("e");
//
//				orderedService.setStartDate(LocalDateTime.now());
//				
//				System.out.println("sdsdsd");
//				
				list.addAll(workFlowService.findChildrenIds(startingTask.getId(),serviceId));
				
//				List<Integer> list2 = new ArrayList<Integer>();
//				list2 = workFlowService.findChildrenIds(taskId, serviceId);
//				
//				System.out.println("sdksdjksdjskdjskdjskdjskdskdjskdj");
//				System.out.println(list2);
				
				
			});
		
		return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
			
		
	}

}
