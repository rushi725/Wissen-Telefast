package com.telefast.sfs.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.telefast.sfs.model.Customer;
import com.telefast.sfs.model.EmpRole;
import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Project;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.CustomerRepository;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.OredredTaskTeamRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;
import com.telefast.sfs.repository.TasksRepository;
import com.telefast.sfs.repository.TeamRepository;
import com.telefast.sfs.service.TaskService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/tasks")
public class TaskController {

	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private TaskService taskService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private TasksRepository tasksRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private OrderedServiceRepository orderedServiceRepository;

	@Autowired
	private OrderedTaskRepository orderedTaskRepository;

	@Autowired
	private ServiceWorkflowRepository serviceWorkFlowRepository;

	@Autowired
	private OredredTaskTeamRepository orderedTaskTeamRepository;

	@PostMapping("/add")
	public void add() {
//		Customer customer = new Customer("fname1", "lname1", "address", "1234567890");
//		customerRepository.save(customer);
////
//		Service service = new Service("service1", "description1");
//		serviceRepository.save(service);
//
//		Task task = new Task("task2", "description2", LocalDateTime.now(), LocalDateTime.now(), true);
//		tasksRepository.save(task);
//
//		Team team = new Team("name", "description");
//		teamRepository.save(team);
		
//		Team team = teamRepository.findById(11).get();

//		
//
//		Employee employee = new Employee(null, "Mayuri", "Pawar", EmpRole.ROLE_PROJECT_MANAGER, "Mumbai", "12342323231",
//				false);
//		employeeRepository.save(employee);
//		Customer customer = new Customer("fname1", "lname1", "address", "1234567890");
//		customerRepository.save(customer);
//
//		Service service = new Service("service1", "description1");
//		serviceRepository.save(service);
		
//
//		Task task = new Task("task2", "description2", LocalDateTime.now(), LocalDateTime.now(), true);
//		tasksRepository.save(task);
//
//		Team team = new Team("name", "description");
//		teamRepository.save(team);

////		
////
//		Employee employee = new Employee(team, "firstName1", "lastName1", EmpRole.ROLE_SUPER, "empAddress1",
//				"1234567891", false);
//		employeeRepository.save(employee);

		Customer customer = customerRepository.findById(16).get();
//		Employee employee = employeeRepository.findById(120).get();
//		
//		Project project = new Project("Testing Project", LocalDateTime.now(), LocalDateTime.now(), Status.NOT_STARTED, 50,
//				customer, employee);
//		projectRepository.save(project);
////
//		OrderedService orderedService = new OrderedService("installationAddress", Status.IN_PROGRESS, 40,
//				LocalDateTime.now(), LocalDateTime.now(), "serviceDenialReason", service, project, employee);
//		orderedServiceRepository.save(orderedService);

////
//		OrderedTask orderedTask = new OrderedTask(Status.NOT_STARTED, LocalDateTime.now(), LocalDateTime.now(),
//				"taskDenialReason", true, task, orderedService, null);

//		orderedTaskRepository.save(orderedTask);
		
//		OrderedService orderedService = orderedServiceRepository.findById(7).get();
		
//		Service service = serviceRepository.findById(55).get();
//		
//		Task task = tasksRepository.findById(54).get();
//
//		Team team2 = new Team();
//		team2 = teamRepository.findById(4).get();
//
////
//		ServiceWorkflow serviceWorkflow = new ServiceWorkflow( 3, 18, task, team2, service);
//		serviceWorkFlowRepository.save(serviceWorkflow);
		
//
//		OrderedTaskTeam orderedTaskTeam = new OrderedTaskTeam( team, orderedTask);
//		orderedTaskTeamRepository.save(orderedTaskTeam);
	}

	@GetMapping
	public List<Task> getTask() {
		return tasksRepository.findAll();
	}
	
	//get task info by taskId
	@GetMapping("/{taskId}")
	public ResponseEntity<?> getTask(@PathVariable int taskId){
		return new ResponseEntity<>(tasksRepository.findById(taskId).get(), HttpStatus.ACCEPTED);
	}

	@PostMapping
	public ResponseEntity<?> addTask(@RequestBody Task task) {
		return new ResponseEntity<>(tasksRepository.save(task), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{taskId}")
	public void deleteTask(@PathVariable int taskId){
//		return new ResponseEntity<>(tasksRepository.delete(task), HttpStatus.ACCEPTED);
		Task task = tasksRepository.findById(taskId).get();
		tasksRepository.delete(task);
	}

	// -------------------------------------------------------------------
	// Team member
	// -------------------------------------------------------------------


////	
//	@GetMapping(value = "/{filter}")
//	public ResponseEntity<?> getTasksByFilter(@PathVariable String filter){
//		return new ResponseEntity<>(orderedTaskRepository.findbyStatus(filter), HttpStatus.FOUND);
//	}

}
