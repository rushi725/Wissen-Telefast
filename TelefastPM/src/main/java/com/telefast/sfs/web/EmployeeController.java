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

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.TeamRepository;
import com.telefast.sfs.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private EmployeeService employeeService;
	@GetMapping
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/{empId}")
	public Employee getEmployee(@PathVariable String empId) {
		return employeeRepository.findById(Integer.parseInt(empId)).get();
	}
	
	
	//get all available employees from teamId
	@GetMapping("/{teamId}/employees")
	public ResponseEntity<?> getAllEmployees(@PathVariable int teamId){
		List<Employee> list = employeeService.allAvailableEmployees(teamId);
		return new ResponseEntity<>(list, HttpStatus.ACCEPTED);

	}
	@GetMapping("/id/{empContactNo}")
	public int getEmployeeId(@PathVariable String empContactNo) {
		return employeeRepository.getEmpId(empContactNo);
	}

	
	@PostMapping("/{teamId}")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee,@PathVariable int teamId) {
		Team team =teamRepo.findById(teamId).get();
		employee.setAvailableStatus(true);
		employee.setTeam(team);
		return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
	}

}
