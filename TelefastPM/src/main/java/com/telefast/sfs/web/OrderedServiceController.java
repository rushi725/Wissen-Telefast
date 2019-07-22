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

import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;
import com.telefast.sfs.service.OrderedServiceService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/orderedServices")
public class OrderedServiceController {
	
	@Autowired
	private OrderedServiceService orderedServiceService;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ServiceRepository serviceRepository; 
	
	@PutMapping("/{orderedServiceId}/cancel")
	public ResponseEntity<?> cancelService(@RequestBody String reason,@PathVariable int orderedServiceId) {

		boolean b = orderedServiceService.cancelOrderedService(reason, orderedServiceId);
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderedServiceId}/complete")
	public ResponseEntity<?> completeService(@PathVariable int orderedServiceId){
	
		boolean b = orderedServiceService.completeService(orderedServiceId);
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public List<OrderedService> getOrderedServices(){
		return orderedServiceRepository.findAll();
	}
	
	//get orderedServices by serviceManagerId
	@GetMapping("/serviceManager/{serviceManagerId}")
	public ResponseEntity<?> getOrderedServicesByServiceManager(@PathVariable int serviceManagerId){
		List<OrderedService> list = orderedServiceService.findAllOrderedServicesByServiceManagerId(serviceManagerId);
		return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
	}
	
	//get orderedServices by projectManagerId
	@GetMapping("/projectManager/{projectManagerId}")
	public ResponseEntity<?> getOrderedServicesByProjectManagerId(@PathVariable int projectManagerId){
		List<OrderedService> list = orderedServiceService.findAllOrderedServiceByProjectManagerId(projectManagerId);
		return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
	}

	@PostMapping
	public ResponseEntity<?> addService(@RequestBody OrderedService orderedService) {
		boolean b = orderedServiceService.addService(orderedService);
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}
}
