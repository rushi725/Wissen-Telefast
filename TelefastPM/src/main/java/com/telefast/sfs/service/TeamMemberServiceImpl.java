package com.telefast.sfs.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.TasksRepository;

public class TeamMemberServiceImpl implements TeamMemberService {
	
	@Autowired
	private TasksRepository tasksRepository;
	
	@Autowired
	private OrderedTaskRepository orderedTasksRepository;

	@Override
	public Task getTask(int id) {
		return null;
	}


}
