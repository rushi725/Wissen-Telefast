package com.telefast.sfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;

@Repository
public class WorkflowServiceImpl implements WorkflowService{
	@Autowired
	private ServiceWorkflowRepository serviceWorkflowRepository;
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Override
	public void defineWorkflow(List<ServiceWorkflow> workflows) {
		for(ServiceWorkflow serviceWorkflow:workflows) {
			serviceWorkflowRepository.save(serviceWorkflow);
		}
	}

	@Override
	public Task getFirstTask(int serviceId) {
		
		Task task = serviceWorkflowRepository.getFirstTask(serviceId);
		
		return task;
	}

//	@Override
//	public List<Integer> findChildrenIds(int taskId, int serviceId) {
//		
//		List<Integer> list = serviceWorkflowRepository.findChildrenIds(taskId, serviceId);
//		
//		return list;
//	}

	@Override
	public List<Task> findChildrensByPrevTask(int orderedTaskId) {
		
		OrderedTask orderedTask = orderedTaskRepository.findById(orderedTaskId).get();
		
		OrderedService orderedService = orderedTask.getOrderedService();
		
		Service service = orderedService.getService();
		int serviceId = service.getId();
		
		int taskId = orderedTask.getTask().getId();
		
		List<Task> childrenTaskList = serviceWorkflowRepository.findChildrenIds(taskId, serviceId);

			
		return childrenTaskList;
	}



}
