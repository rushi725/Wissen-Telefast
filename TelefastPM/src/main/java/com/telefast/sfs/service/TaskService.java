package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.web.OrderedTaskRequest;

public interface TaskService {

	Boolean transferTask(int taskId, int toEmployeeID);

	List<ServiceWorkflow> dependsOn(Task task, Service service, Team team);

	boolean assignTaskToTeam(int serviceId, int taskId, int teamId,int sequenceNo);

	String assignTaskToEmployee(int taskId, int serviceId, int orderedServiceId);
	
	OrderedTaskRequest getAllInfoForEmployeeId(int employeeId);
	
	List<OrderedTask> getOrderedTaskAssignedByTeamManager(int teamManagerId);

}
