package com.telefast.sfs.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.Project;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/services")
public class ServiceController {
	
private RestTemplate restTemplate= new RestTemplate();
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Autowired
	private ServiceWorkflowRepository serviceWorkFlowRepository;
	
	//get service by serviceId
//	@GetMapping("/{serviceId}")
//	public Service getService(@PathVariable String serviceId) {
//		return serviceRepository.findById(Integer.parseInt(serviceId)).get();
//	}
	
	@GetMapping
	public ResponseEntity<?> getServices(){
		return new ResponseEntity<>(serviceRepository.findAll(), HttpStatus.ACCEPTED);
	}
	
	//get all tasks by serviceId
	@GetMapping("/{serviceId}/tasks")
	public ResponseEntity<?> getTasksByServiceId(@PathVariable String serviceId){
		List<Task> tasks = serviceWorkFlowRepository.findTasksByServiceId(Integer.parseInt(serviceId));
		return new ResponseEntity<>(tasks, HttpStatus.ACCEPTED);
	}
	
	//get all services assigned to service manager
	@GetMapping(value = "/{serviceManagerId}")
	public ResponseEntity<?> getServiceByManager(@PathVariable String serviceManagerId) {
		List<Service> services = orderedServiceRepository.findAllByManagerId(Integer.parseInt(serviceManagerId));
		return new ResponseEntity<>(services, HttpStatus.ACCEPTED);
	}
	
	//add service
	@PostMapping
	public ResponseEntity<?> addService(@RequestBody Service service){
		return new ResponseEntity<>(serviceRepository.save(service), HttpStatus.CREATED);
	}
	
	
//	
//	@GetMapping(value = "/{filter}")
//	public ResponseEntity<?> getTasksByFilter(@PathVariable String filter){
//		return new ResponseEntity<>(orderedTaskRepository.findbyStatus(filter), HttpStatus.FOUND);
//	}
}
