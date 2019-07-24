package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Task;

public interface WorkflowService {
	
	void defineWorkflow(List<ServiceWorkflow> workflows);

	List<Task> getFirstTask(int serviceId);

	
//	List<Integer> findChildrenIds(int taskId, int serviceId);

	List<Task> findChildrensByPrevTask(int orderedTaskId);
	
}
