package com.telefast.sfs.web;

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
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.ServiceRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/services")
public class ServiceController {
	

	@Autowired
	private ServiceRepository serviceRepository;
	
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
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

	
//	
//	@GetMapping(value = "/{filter}")
//	public ResponseEntity<?> getTasksByFilter(@PathVariable String filter){
//		return new ResponseEntity<>(orderedTaskRepository.findbyStatus(filter), HttpStatus.FOUND);
//	}
}
