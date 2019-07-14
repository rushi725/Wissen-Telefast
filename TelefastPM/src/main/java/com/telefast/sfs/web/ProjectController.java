package com.telefast.sfs.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Customer;
import com.telefast.sfs.model.Project;
import com.telefast.sfs.repository.CustomerRepository;
import com.telefast.sfs.repository.ProjectRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/projects")
public class ProjectController {
	
private ProjectRepository projectRepository;
private CustomerRepository customerRepository;
	
	@GetMapping(value = "/{projectManagerId}")
	public ResponseEntity<?> getProject(){
		return new ResponseEntity<>(projectRepository.findAll(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> addProject(@RequestBody Project project){
		return new ResponseEntity<>(projectRepository.save(project), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
		return new ResponseEntity<>(customerRepository.save(customer),HttpStatus.CREATED);
	}
	

}
