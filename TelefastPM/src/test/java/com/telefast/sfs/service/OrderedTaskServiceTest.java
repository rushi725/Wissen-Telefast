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
import com.telefast.sfs.model.OrderedTask;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TelefastPMApplication.class)
public class OrderedTaskServiceTest {
	@Autowired
	private OrderedTaskService orderedTaskService;

	@Before
	public void init() {

	}

	@Test
	public void transferTaskTest() {
		assertTrue(orderedTaskService.transferTask(1, 34));
	}

	@Test
	public void cancelTaskTask() {
		assertTrue(orderedTaskService.cancelTask("test", 1));
	}

	@Test
	public void rejectTaskTest() {
		assertTrue(orderedTaskService.rejectTask(1));
	}

	@Test
	public void approveTaskTest() {
		assertTrue(orderedTaskService.approveTask(1));
	}

	@Test
	public void changeStatusTest() {
		assertTrue(orderedTaskService.changeStatus(1, 5));
	}

	@Test
	public void getOrderedTaskAssignedToTeamManagerTest() {
		List<OrderedTask> orderedTasks = orderedTaskService.getOrderedTaskAssignedToTeamManager(4);
		assertTrue(orderedTasks.stream().allMatch(e -> e.getEmployee().getId() == 4));
	}
}
