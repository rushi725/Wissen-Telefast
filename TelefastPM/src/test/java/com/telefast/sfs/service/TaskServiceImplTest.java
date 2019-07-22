package com.telefast.sfs.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telefast.sfs.TelefastPMApplication;
import com.telefast.sfs.model.OrderedTask;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TelefastPMApplication.class)
public class TaskServiceImplTest {
       
	@Autowired
	OrderedTaskService orderedTaskService;
	
	
	@Test
	public void checkGetList() {
	   int managerId = 14;
	  List<OrderedTask> actualList = orderedTaskService.getOrderedTaskAssignedToTeamManager(managerId);
	  int actual = actualList.size();
	  
	  int expected = 3;
	  
	  assertEquals(expected, actual);
	  	  
	  
	}
	
	
}
