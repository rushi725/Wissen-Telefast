package com.telefast.sfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.ServiceWorkflowRepository;

@Repository
public class WorkflowServiceImpl implements WorkflowService{
	@Autowired
	ServiceWorkflowRepository serviceWorkflowRepository;
	
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

	@Override
	public List<Integer> findChildrenIds(int taskId, int serviceId) {
		
		List<Integer> list = serviceWorkflowRepository.findChildrenIds(taskId, serviceId);
		
		
		return list;
	}



}
