package com.telefast.sfs.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Team;

public interface WorkflowService {
	
	void defineWorkflow(List<ServiceWorkflow> workflows);
	
}
