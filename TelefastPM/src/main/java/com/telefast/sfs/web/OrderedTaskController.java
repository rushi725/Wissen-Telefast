package com.telefast.sfs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.TasksRepository;
import com.telefast.sfs.service.TaskService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sfs/orderedTask")
public class OrderedTaskController {
	
}
