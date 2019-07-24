package com.telefast.sfs.service;

import com.telefast.sfs.model.OrderedTask;

public interface TaskService {


//	List<ServiceWorkflow> dependsOn(Task task, Service service, Team team);

	boolean assignTaskToTeam(int serviceId, int taskId, int teamId,int sequenceNo);
	
	boolean assignOrderedTask(OrderedTask orderedTask);

//	boolean assignTaskToEmployee(int taskId, int serviceId, int orderedServiceId);
	
//	Task getTaskByEmploye(int employeeId);

}
