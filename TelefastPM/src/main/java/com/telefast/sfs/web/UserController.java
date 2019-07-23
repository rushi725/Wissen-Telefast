package com.telefast.sfs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.User;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeRepository employeeRepo;

//	@PostMapping
//	public ResponseEntity<?> addUser(@RequestBody User user) {
//		return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
//	}
	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable int userId){
		userRepository.deleteById(userId);
	}


	@PostMapping("/register/{empId}")
	public ResponseEntity<?> addUser(@RequestBody User user,@PathVariable int empId) {
		Employee employee = employeeRepo.findById(empId).get();
		user.setEmployee(employee);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
	}

	
}
