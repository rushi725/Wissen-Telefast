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
import com.telefast.sfs.model.Project;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.repository.CustomerRepository;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.ProjectRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/projects")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;


	@Autowired
	private CustomerRepository customerRepository;

	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;

	@GetMapping
	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

	//get services by projectId
	@GetMapping(value = "/{projectId}/services")
	public ResponseEntity<?> getServices(@PathVariable String projectId){
		List<Service> services = orderedServiceRepository.findServicesByProjectId(Integer.parseInt(projectId));
		return new ResponseEntity<>(services, HttpStatus.ACCEPTED);
	}
	
	//get all projects by managerId
	@GetMapping(value = "/{projectManagerId}")
	public ResponseEntity<?> getProject(@PathVariable String projectManagerId) {
		List<Project> projects = projectRepository.findProjectsByManagerId(Integer.parseInt(projectManagerId));
		return new ResponseEntity<>(projects.get(0), HttpStatus.ACCEPTED);
		
	}

	@PostMapping
	public ResponseEntity<?> addProject(@RequestBody Project project) {
		Employee projectManager = employeeRepository.findById(project.getProjectManager().getId()).get();
		project.setProjectManager(projectManager);
		return new ResponseEntity<>(projectRepository.save(project), HttpStatus.CREATED);
	}

}
