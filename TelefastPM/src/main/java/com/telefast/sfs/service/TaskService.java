package com.telefast.sfs.service;

import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;

public interface TaskService {
	Boolean transferTask(int taskId, int toEmployeeID);
	String assignTask(Task task,Team team,OrderedService orderedService); 
}
