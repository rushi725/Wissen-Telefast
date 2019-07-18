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

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.Project;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/orderedServices")
public class OrderedServiceController {
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ServiceRepository serviceRepository; 
	
	@PutMapping("/{orderedServiceId}/cancel")
	public ResponseEntity<?> cancelService(@RequestBody String reason,@PathVariable String orderedServiceId) {
		OrderedService orderedService = orderedServiceRepository.findById(Integer.parseInt(orderedServiceId)).get();
		orderedService.setServiceDenialReason(reason);
		orderedService.setServiceStatus(Status.CANCELLED);
		orderedServiceRepository.save(orderedService);
		return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderedServiceId}/complete")
	public ResponseEntity<?> completeService(@PathVariable String orderedServiceId){
		OrderedService orderedService = orderedServiceRepository.findById(Integer.parseInt(orderedServiceId)).get();
		orderedService.setServiceStatus(Status.CANCELLED);
		orderedServiceRepository.save(orderedService);
		return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public List<OrderedService> getOrderedServices(){
		return orderedServiceRepository.findAll();
	}
	
	@PostMapping
	public void addService(@RequestBody OrderedService orderedService) {
		Service service = new Service();
		Project project = new Project();
		Employee serviceManager = new Employee();

		service = serviceRepository.findById(orderedService.getService().getId()).get();
		project = projectRepository.findById(orderedService.getProject().getProjectId()).get();
		serviceManager = employeeRepository.findById(orderedService.getEmployee().getId()).get();

		orderedService.setService(service);
		orderedService.setProject(project);
		orderedService.setEmployee(serviceManager);

		orderedServiceRepository.save(orderedService);
	}
}
