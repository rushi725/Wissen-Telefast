package com.telefast.sfs.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telefast.sfs.TelefastPMApplication;
import com.telefast.sfs.model.OrderedService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TelefastPMApplication.class)
public class OrderedServiceServiceTest {
//
//	@Autowired
//	private ProjectRepository projectRepository;
//
//	@Autowired
//	private ServiceRepository serviceRepository;
//
//	@Autowired
//	private EmployeeRepository employeeRepository;

	@Autowired
	private OrderedServiceService orderedServiceService;

//	@Autowired
//	private TeamRepository teamRepository;
//	@Autowired
//	private CustomerRepository customerRepository;

	@Before
	public void init() {

	}

	@Test
	public void cancelOrderedServiceTest() {
		boolean actual = orderedServiceService.cancelOrderedService("testing", 32);
		assertTrue(actual);
	}
 
	@Test
	public void findAllOrderedServicesByServiceManagerIdTest() {
		List<OrderedService> orderedServices = orderedServiceService.findAllOrderedServicesByServiceManagerId(14);
		assertTrue(orderedServices.stream().allMatch(os -> os.getEmployee().getId() == 14));
	}

	@Test
	public void findAllOrderedServiceByProjectManagerIdTest() {
		List<OrderedService> orderedServices = orderedServiceService.findAllOrderedServiceByProjectManagerId(16);
		assertTrue(orderedServices.stream().allMatch(e -> e.getProject().getProjectManager().getId() == 16));
	}

//	@Test
//	public void addServiceTest() {
//
//		Customer customer = new Customer("fname1", "lname1", "test1", "1");
//		customerRepository.save(customer);
//		Team team = new Team("testTeam1", "description");
//		teamRepository.save(team);
//		Service service = new Service("testService1", "description1");
//		serviceRepository.save(service);
//
//		Employee employee = new Employee();
//		employee.setAvailableStatus(true);
//		employee.setFirstName("firstName1");
//		employee.setLastName("lastName1");
//		employee.setEmpRole(EmpRole.ROLE_SERVICE_MANAGER);
//		employee.setEmpAddress( "empAddress1");
//		employee.setEmpContactNo("1234567892");
//		employee.setTeam(team);
//		Employee employee1 = new Employee();
//		employee1.setAvailableStatus(true);
//		employee1.setFirstName("firstName1");
//		employee1.setLastName("lastName1");
//		employee1.setEmpRole(EmpRole.ROLE_PRODUCT_MANAGER);
//		employee1.setEmpAddress( "empAddress1");
//		employee1.setEmpContactNo("1234562");
//		employee1.setTeam(team);
//		employeeRepository.save(employee);
//		employeeRepository.save(employee1);
//
//		Project project = new Project("testName", LocalDateTime.now(), LocalDateTime.now(), Status.IN_PROGRESS, 50,
//				customer, employee1);
//		projectRepository.save(project);
//		OrderedService orderedService = new OrderedService("installationAddress", Status.IN_PROGRESS, 40, LocalDateTime.now(),
//				LocalDateTime.now(), "serviceDenialReason", service, project, employee);
//		
//		assertTrue(orderedServiceService.addService(orderedService));
//	}

	@Test
	public void completeServiceTest() {
		assertTrue(orderedServiceService.completeService(32));
	}

}
