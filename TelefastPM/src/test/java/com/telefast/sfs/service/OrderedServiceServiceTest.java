package com.telefast.sfs.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telefast.sfs.TelefastPMApplication;
import com.telefast.sfs.model.Customer;
import com.telefast.sfs.model.EmpRole;
import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.Project;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.CustomerRepository;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;
import com.telefast.sfs.repository.TeamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TelefastPMApplication.class)
public class OrderedServiceServiceTest {

	
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private OrderedServiceService orderedServiceService;

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Before
	public void init() {

	}

	

	@Test
	public void findAllOrderedServicesByServiceManagerIdTest() {
		List<OrderedService> orderedServices = orderedServiceService.findAllOrderedServicesByServiceManagerId(58);
		assertTrue(orderedServices.stream().allMatch(os -> os.getEmployee().getId() == 58));
	}

	@Test
	public void findAllOrderedServiceByProjectManagerIdTest() {
		List<OrderedService> orderedServices = orderedServiceService.findAllOrderedServiceByProjectManagerId(58);
		assertTrue(orderedServices.stream().allMatch(e -> e.getProject().getProjectManager().getId() == 58));
	}

	@Test
	public void addServiceTest() {

		Customer customer = new Customer("fname1", "lname1", "test1", "1");
		customerRepository.save(customer);
		Team team = new Team("testTeam1", "description");
		teamRepository.save(team);
		Service service = new Service("testService1", "description1");
		serviceRepository.save(service);

		Employee employee = new Employee();
		employee.setAvailableStatus(true);
		employee.setFirstName("firstName1");
		employee.setLastName("lastName1");
		employee.setEmpRole(EmpRole.ROLE_SERVICE_MANAGER);
		employee.setEmpAddress( "empAddress1");
		employee.setEmpContactNo("1234567892");
		employee.setTeam(team);
		Employee employee1 = new Employee();
		employee1.setAvailableStatus(true);
		employee1.setFirstName("firstName1");
		employee1.setLastName("lastName1");
		employee1.setEmpRole(EmpRole.ROLE_PRODUCT_MANAGER);
		employee1.setEmpAddress( "empAddress1");
		employee1.setEmpContactNo("1234562");
		employee1.setTeam(team);
		employeeRepository.save(employee);
		employeeRepository.save(employee1);

		Project project = new Project("testName", LocalDate.now(), LocalDate.now(), Status.IN_PROGRESS, 50,
				customer, employee1);
		projectRepository.save(project);
		OrderedService orderedService = new OrderedService("installationAddress", Status.IN_PROGRESS, 40, LocalDate.now(),
				LocalDate.now(), "serviceDenialReason", service, project, employee);
	
		assertTrue(orderedServiceService.addService(orderedService));
	}
	
	@Test
	public void updateProgressTest() {
		Customer customer = new Customer("fname1", "lname1", "test1", "1");
		customerRepository.save(customer);
		Team team = new Team("testTeam1", "description");
		teamRepository.save(team);
		Service service = new Service("testService1", "description1");
		serviceRepository.save(service);

		Employee employee = new Employee();
		employee.setAvailableStatus(true);
		employee.setFirstName("firstName1");
		employee.setLastName("lastName1");
		employee.setEmpRole(EmpRole.ROLE_SERVICE_MANAGER);
		employee.setEmpAddress( "empAddress1");
		employee.setEmpContactNo("1234567892");
		employee.setTeam(team);
		Employee employee1 = new Employee();
		employee1.setAvailableStatus(true);
		employee1.setFirstName("firstName1");
		employee1.setLastName("lastName1");
		employee1.setEmpRole(EmpRole.ROLE_PRODUCT_MANAGER);
		employee1.setEmpAddress( "empAddress1");
		employee1.setEmpContactNo("1234562");
		employee1.setTeam(team);
		employeeRepository.save(employee);
		employeeRepository.save(employee1);

		Project project = new Project("testName", LocalDate.now(), LocalDate.now(), Status.IN_PROGRESS, 50,
				customer, employee1);
		projectRepository.save(project);
		OrderedService orderedService = new OrderedService("installationAddress", Status.IN_PROGRESS, 40, LocalDate.now(),
				LocalDate.now(), "serviceDenialReason", service, project, employee);
		orderedServiceService.updateProgress(orderedService);
		assertTrue(true);
	}

	@Test
	public void completeServiceTest() {
		assertTrue(orderedServiceService.completeService(60));
	}

}
