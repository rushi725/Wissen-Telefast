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
import org.springframework.web.client.RestTemplate;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.Project;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/services")
public class ServiceController {
	
private RestTemplate restTemplate= new RestTemplate();
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	OrderedServiceRepository orderedServiceRepository;
	
	@GetMapping("/{serviceId}")
	public Service getService(@PathVariable String serviceId) {
		return serviceRepository.findById(Integer.parseInt(serviceId)).get();
	}
	
	@GetMapping
	public ResponseEntity<?> getServices(){
		return new ResponseEntity<>(serviceRepository.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/{serviceManagerId}")
	public ResponseEntity<?> getServiceByManager(@PathVariable String serviceManagerId) {
		Service  service = new Service();
		service=serviceRepository.findById(Integer.parseInt(serviceManagerId)).get();
		ResponseEntity<Service> responseEntity = new ResponseEntity<Service>(service, HttpStatus.FOUND);
		return responseEntity;
	}
	
	@GetMapping("/orderedServices")
	public ResponseEntity<?> getOrderedServices() {
		return new ResponseEntity<>(orderedServiceRepository.findAll(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> addService(@RequestBody Service service){
		return new ResponseEntity<>(serviceRepository.save(service), HttpStatus.CREATED);
	}
	
	@PostMapping("/ordered_service")
	public void add(@RequestBody OrderedService orderedService) {
		Service service = new Service();
		Project project = new Project();
		Employee serviceManager = new Employee();

		service = serviceRepository.findById(orderedService.getService().getId()).get();
		project = projectRepository.findById(orderedService.getProject().getProjectId()).get();
		serviceManager = employeeRepository.findById(orderedService.getEmployee().getId()).get();

		orderedService.setService(service);
		orderedService.setProject(project);
		orderedService.setEmployee(serviceManager);

		System.out.println(orderedService);
		orderedServiceRepository.save(orderedService);
	}
	
//	
//	@GetMapping(value = "/{filter}")
//	public ResponseEntity<?> getTasksByFilter(@PathVariable String filter){
//		return new ResponseEntity<>(orderedTaskRepository.findbyStatus(filter), HttpStatus.FOUND);
//	}
}
