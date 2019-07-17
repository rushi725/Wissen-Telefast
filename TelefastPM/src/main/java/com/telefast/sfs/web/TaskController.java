package com.telefast.sfs.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Customer;
import com.telefast.sfs.model.EmpRole;
import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.OrderedTaskTeam;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/tasks")
public class TaskController {

	

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
		Customer customer = new Customer("fname1", "lname1", "address", "1234567890");
		customerRepository.save(customer);
//
		Service service = new Service( "service1", "description1");
		serviceRepository.save(service);
//
		Task task = new Task( "task2", "description2", LocalDateTime.now(), LocalDateTime.now(), true);
		tasksRepository.save(task);
//
//		Team team = new Team("name", "description");
//		teamRepository.save(team);
		
		Team team2 = new Team();
		team2 = teamRepository.findById(4).get();
//		
//
		Employee employee = new Employee(team2, "firstName2", "lastName2", EmpRole.TEAM_MEMBER, "empAddress2",
				"1234567891", false);
		employeeRepository.save(employee);

		Project project = new Project( "name", LocalDateTime.now(), LocalDateTime.now(), Status.IN_PROGRESS, 50,
				customer, employee);
		projectRepository.save(project);
//
		OrderedService orderedService = new OrderedService("installationAddress", Status.IN_PROGRESS, 40,
				LocalDateTime.now(), LocalDateTime.now(), "serviceDenialReason", service, project, employee);
		orderedServiceRepository.save(orderedService);
//
		OrderedTask orderedTask = new OrderedTask( Status.NOT_STARTED, LocalDateTime.now(), LocalDateTime.now(),
				"taskDenialReason", false, task, orderedService, null);
		orderedTaskRepository.save(orderedTask);
//
//		ServiceWorkflow serviceWorkflow = new ServiceWorkflow( 1, 2, task, team, service);
//		serviceWorkFlowRepository.save(serviceWorkflow);
//
//		OrderedTaskTeam orderedTaskTeam = new OrderedTaskTeam( team, orderedTask);
//		orderedTaskTeamRepository.save(orderedTaskTeam);
	}

	
	@PostMapping
	public  ResponseEntity<?> addTask(@RequestBody Task task) {
		return new ResponseEntity<>(tasksRepository.save(task), HttpStatus.CREATED);
	}

	@PostMapping("/ordered_task")
	public void add(@RequestBody OrderedTask orderedTask) {
		Task task = new Task();
		OrderedService orderedService = new OrderedService();
		Employee teamMember = new Employee();
		System.out.println(orderedTask.getOrderedService().getOrderId());

		task = tasksRepository.findById(orderedTask.getTask().getId()).get();
		orderedService = orderedServiceRepository.findById(orderedTask.getOrderedService().getOrderId()).get();
		teamMember = employeeRepository.findById(orderedTask.getEmployee().getId()).get();

		System.out.println(orderedTask);

		orderedTask.setTask(task);
		orderedTask.setOrderedService(orderedService);
		orderedTask.setEmployee(teamMember);

		orderedTaskRepository.save(orderedTask);
	}

	@PostMapping("/service_work_flow")
	public void add(@RequestBody ServiceWorkflow serviceWorkFlow) {

		Service service = new Service();
		Task task = new Task();
		Team team = new Team();

		service = serviceRepository.findById(serviceWorkFlow.getService().getId()).get();
		task = tasksRepository.findById(serviceWorkFlow.getTask().getId()).get();
		team = teamRepository.findById(serviceWorkFlow.getTeam().getId()).get();

		serviceWorkFlow.setService(service);
		serviceWorkFlow.setTask(task);
		serviceWorkFlow.setTeam(team);

		serviceWorkFlowRepository.save(serviceWorkFlow);
	}

	@PostMapping("/ordered_task_team")
	public void add(@RequestBody OrderedTaskTeam orderedTaskTeam) {

		Team team = new Team();
		OrderedTask orderedTask = new OrderedTask();

		team = teamRepository.findById(orderedTaskTeam.getTeam().getId()).get();
		orderedTask = orderedTaskRepository.findById(orderedTaskTeam.getOrderedTask().getOrderTaskId()).get();

		orderedTaskTeam.setTeam(team);
		orderedTaskTeam.setOrderedTask(orderedTask);

		orderedTaskTeamRepository.save(orderedTaskTeam);
	}

	@GetMapping("/ordered_Task_Team")
	public List<OrderedTaskTeam> get5() {
		return orderedTaskTeamRepository.findAll();
	}

	@GetMapping("/project")
	public List<Project> get3() {
		return projectRepository.findAll();
	}

	// -------------------------------------------------------------------
	// Team member
	// -------------------------------------------------------------------

//	//get task by member
//	@GetMapping(value = "/{employeeId}")
//	public ResponseEntity<?> getTask(@PathVariable String employeeId) {
//				
//		OrderedTask orderedtask = new OrderedTask();
//		Employee employee = new Employee();
//		employee = employeeRepository.findById(Integer.parseInt(employeeId)).get();
//		
//		
//		//orderedtask=orderedTaskRepository.findByApproved(true).get();
////		
////		ResponseEntity<Task> responseEntity = new ResponseEntity<Task>(task, HttpStatus.FOUND);
////		return responseEntity;
//	}
//	
//	
//	//get all tasks assigned to a team
//	@GetMapping(value = "/{teamManagerid}/tasks")
//	public ResponseEntity<?> getTasks(@PathVariable String teamManagerId){
//		return new ResponseEntity<>(orderedTaskRepository.find, HttpStatus.CREATED);
//	}
//	
//	
//	
//	@PostMapping
//	public ResponseEntity<?> addTask(@RequestBody Task task){
//		return new ResponseEntity<>(tasksRepository.save(task), HttpStatus.CREATED);
//	}
//	
//	
////	
////	@GetMapping(value = "/{filter}")
////	public ResponseEntity<?> getTasksByFilter(@PathVariable String filter){
////		return new ResponseEntity<>(orderedTaskRepository.findbyStatus(filter), HttpStatus.FOUND);
////	}
//

}
