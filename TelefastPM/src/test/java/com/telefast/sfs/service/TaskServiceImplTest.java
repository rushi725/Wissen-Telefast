package com.telefast.sfs.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telefast.sfs.TelefastPMApplication;

	@RunWith(SpringRunner.class)
	@SpringBootTest(classes = TelefastPMApplication.class)
	public class TaskServiceImplTest {

		@Autowired
		private TaskService taskService;

//		@Autowired
//		private TasksRepository tasksRepository;

		@Before
		public void init() {

		}

		@Test
		public void testAssignTaskToTeamTest() {
			assertTrue(taskService.assignTaskToTeam(25, 18, 3, 2));
		}


//		@Test
//		public void getTaskByEmployeeTest() {
//			Task expectedTask = tasksRepository.findById(1).get();
//			Task actualTask = taskService.getTaskByEmployee(29);
//			assertTrue(expectedTask.equals(actualTask));
//		}
	}
	

