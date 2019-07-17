package com.telefast.sfs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Customer;
import com.telefast.sfs.repository.CustomerRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer ) {
		return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
		
	}
}
