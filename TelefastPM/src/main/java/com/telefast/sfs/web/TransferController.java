package com.telefast.sfs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.TasksRepository;
import com.telefast.sfs.service.TaskService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sfs/transferTask")
public class TransferController {

	@Autowired
	OrderedTaskRepository orderedTaskRepository;

	@Autowired
	TasksRepository tasksRepository;

	@Autowired
	TaskService taskService;

	@PutMapping
	public ResponseEntity<?> tranferTask(@RequestBody Task task, @RequestBody Employee employee) {
		int empId = employee.getId();
		int taskId = task.getId();
		boolean b = taskService.transferTask(taskId, empId);
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}

}
