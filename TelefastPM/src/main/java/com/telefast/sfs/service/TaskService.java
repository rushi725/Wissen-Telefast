package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;

public interface TaskService {
	Boolean transferTask(int taskId, int toEmployeeID);
	String assignTask(Task task,Team team,OrderedService orderedService); 
	List<Integer> dependsOn(Task task,Service service,Team team);
}
