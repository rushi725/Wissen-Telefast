package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.ServiceWorkflow;

public interface WorkflowService {
	
	void defineWorkflow(List<ServiceWorkflow> workflows);
	
}
