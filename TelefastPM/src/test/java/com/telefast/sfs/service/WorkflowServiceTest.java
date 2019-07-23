package com.telefast.sfs.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telefast.sfs.TelefastPMApplication;
import com.telefast.sfs.model.Task;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TelefastPMApplication.class)
public class WorkflowServiceTest {

	@Autowired
	private WorkflowService workflowService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void getFirstTaskTest() {
		Task actual = workflowService.getFirstTask(60);
		assertThat(actual instanceof Task);
	}

}
