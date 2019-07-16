package com.telefast.sfs;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

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
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.OrederedServiceRepository;
import com.telefast.sfs.repository.OredredTaskTeamRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;
import com.telefast.sfs.repository.TasksRepository;
import com.telefast.sfs.repository.TeamRepository;

@SpringBootApplication
public class TelefastPMApplication {
	@Autowired
	static
	CustomerRepository customerRepository;
	
	@Autowired
	static
	ServiceRepository serviceRepository;
	
	@Autowired
	static
	TasksRepository tasksRepository;
	
	@Autowired
	static
	TeamRepository teamRepository;
	
	@Autowired
	static
	EmployeeRepository employeeRepository;
	
	@Autowired
	static
	ProjectRepository projectRepository;
	
	@Autowired
	static
	OrederedServiceRepository orederedServiceRepository;
	
	@Autowired
	static
	OrderedTaskRepository orderedTaskRepository;
	
	@Autowired
	static
	ServiceWorkflowRepository serviceWorkflowRepository;
	
	@Autowired
	static
	OredredTaskTeamRepository oredredTaskTeamRepository;

	public static void main(String[] args) {
		SpringApplication.run(TelefastPMApplication.class, args);
				
//		Customer customer = new Customer(1, "fname1", "lname1", "address", "1234567890");
//		Service service = new Service(11, "service1", "description1");
//		Task task = new Task(21, "task1", "escription", LocalDateTime.now(), LocalDateTime.now(), false);
//		Team team = new Team(31, "name", "description");
//		Employee employee = new Employee(41, team, "firstName", "lastName", EmpRole.TEAM_MEMBER, "empAddress", "1234567890", true);
//		Project project = new Project(51, "name", LocalDateTime.now(), LocalDateTime.now(), Status.IN_PROGRESS, 50, customer, employee);
//		OrderedService orderedService = new OrderedService(61, "installationAddress", Status.IN_PROGRESS, 40,  LocalDateTime.now(),  LocalDateTime.now(), "serviceDenialReason", service, project, employee);
//		OrderedTask orderedTask = new OrderedTask(71, Status.IN_PROGRESS,  LocalDateTime.now(),  LocalDateTime.now(), "taskDenialReason", false, task, orderedService, employee);
//		ServiceWorkflow serviceWorkflow = new ServiceWorkflow(81,1, 2, task, team, service);
//		OrderedTaskTeam orderedTaskTeam = new OrderedTaskTeam(81, team, orderedTask);
//		
//		
//		customerRepository.save(customer);
//		serviceRepository.save(service);
//		tasksRepository.save(task);
//		teamRepository.save(team);
//		employeeRepository.save(employee);
//		projectRepository.save(project);
//		orederedServiceRepository.save(orderedService);
//		orderedTaskRepository.save(orderedTask);
//		serviceWorkflowRepository.save(serviceWorkflow);
//		oredredTaskTeamRepository.save(orderedTaskTeam);
		
	}

}
