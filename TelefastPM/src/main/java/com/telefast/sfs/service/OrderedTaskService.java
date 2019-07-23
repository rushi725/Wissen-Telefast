package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.OrderedTask;

public interface OrderedTaskService {
	boolean addOrderedTask(OrderedTask orderedTask);

	boolean changeStatus(int orderedTaskId, int statusId);

	boolean approveTask(int orderedTaskId);
	
	boolean rejectTask(int orderedTaskId);
	
	boolean cancelTask(String reason, int orderedTaskId);
	
	boolean transferTask(int taskId, int toEmployeeID);
	
	OrderedTask getFisrtOrderedTask(int orderedServiceId);

	
	List<OrderedTask> getOrderedTaskAssignedToTeamManager(int teamManagerId);

	OrderedTask findOrderedTaskByTask(Integer taskId, int serviceId);
}
