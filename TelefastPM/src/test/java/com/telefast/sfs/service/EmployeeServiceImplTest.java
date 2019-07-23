package com.telefast.sfs.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telefast.sfs.TelefastPMApplication;
import com.telefast.sfs.model.Employee;
import com.telefast.sfs.web.OrderedTaskRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TelefastPMApplication.class)
public class EmployeeServiceImplTest {

	@Autowired
	private EmployeeService employeeService;

	@Before
	public void init() {

	}

	@Test
	public void TestAllAvailableEmployees() {
		List<Employee> employees = employeeService.allAvailableEmployees(3);
		assertTrue(employees.stream().allMatch(emp -> emp.isAvailableStatus()));
	}

	@Test
	public void TestGetAllInfoForEmployeeId() {
		List<OrderedTaskRequest> orderedTaskRequests = employeeService.getAllInfoForEmployeeId(21);
		assertThat(orderedTaskRequests, instanceOf(List.class));
	}
}
