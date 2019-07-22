package com.telefast.sfs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.ServiceRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;
import com.telefast.sfs.repository.TasksRepository;
import com.telefast.sfs.repository.TeamRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/serviceWorkFlow")
public class ServiceWorkFlowController {
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private TasksRepository tasksRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private ServiceWorkflowRepository serviceWorkFlowRepository;
	
	@GetMapping
	public List<ServiceWorkflow> getServiceWorkFlows(){
		return serviceWorkFlowRepository.findAll();
	}
	
	@GetMapping("/{serviceId}")
	public ResponseEntity<?> getServiceWorkFlowsByService(@PathVariable int serviceId){
		Service service = serviceRepository.findById(serviceId).get();
		return new ResponseEntity<>(serviceWorkFlowRepository.findByService(service),HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping

	public ResponseEntity<?> add(@RequestBody ServiceWorkflow serviceWorkFlow) {
		Service service = new Service();
		Task task = new Task();
		Team team = new Team();

		service = serviceRepository.findById(serviceWorkFlow.getService().getId()).get();
		task = tasksRepository.findById(serviceWorkFlow.getTask().getId()).get();
		team = teamRepository.findById(serviceWorkFlow.getTeam().getId()).get();

		serviceWorkFlow.setService(service);
		serviceWorkFlow.setTask(task);
		serviceWorkFlow.setTeam(team);

		return new ResponseEntity<>(serviceWorkFlowRepository.save(serviceWorkFlow), HttpStatus.CREATED);
	}

}
