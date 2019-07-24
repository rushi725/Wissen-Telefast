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

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Project;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;
import com.telefast.sfs.repository.ServiceWorkflowRepository;
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
	private ServiceWorkflowRepository serviceWorkflowRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ServiceRepository serviceRepository; 
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;
	
	
	@PutMapping("/{orderedServiceId}/complete")
	public ResponseEntity<?> completeService(@PathVariable int orderedServiceId){
	
		boolean b = orderedServiceService.completeService(orderedServiceId);
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public List<OrderedService> getOrderedServices(){
		return orderedServiceRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<?> addService(@RequestBody OrderedService orderedService) {
		Service service = new Service();
		Project project = new Project();
		Employee serviceManager = new Employee();

		service = serviceRepository.findById(orderedService.getService().getId()).get();
		project = projectRepository.findById(orderedService.getProject().getProjectId()).get();
		serviceManager = employeeRepository.findById(orderedService.getEmployee().getId()).get();
		
		orderedService.setService(service);
		orderedService.setProject(project);
		orderedService.setEmployee(serviceManager);

		orderedService = orderedServiceRepository.save(orderedService);
		
		List<ServiceWorkflow> tasks = serviceWorkflowRepository.findByService(service);
		for(ServiceWorkflow workflow : tasks) {
			OrderedTask orderedTask = new OrderedTask();
			orderedTask.setOrderedService(orderedService);
			orderedTask.setTask(workflow.getTask());
			orderedTask.setTaskStatus(Status.NOT_STARTED);
			orderedTaskRepository.save(orderedTask);
		}
		return new ResponseEntity<>(orderedService, HttpStatus.CREATED);
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

//	@PostMapping
//	public ResponseEntity<?> addService(@RequestBody OrderedService orderedService) {
//		boolean b = orderedServiceService.addService(orderedService);
//		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
//>>>>>>> ea009a8bf9913eddf6a424217a0a3eac4b28ebd1
//	}
}
