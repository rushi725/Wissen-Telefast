package com.telefast.sfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.ServiceWorkflow;
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

}
