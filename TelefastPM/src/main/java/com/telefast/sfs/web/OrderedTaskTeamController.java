package com.telefast.sfs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.OrderedTaskTeam;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.OredredTaskTeamRepository;
import com.telefast.sfs.repository.TeamRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sfs/orderedTaskTeam")
public class OrderedTaskTeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;
	
	@Autowired
	private OredredTaskTeamRepository orderedTaskTeamRepository;
	
	@GetMapping
	public List<OrderedTaskTeam> getTaskTeam(){
		return orderedTaskTeamRepository.findAll();
	}

	@PostMapping
	public void add(@RequestBody OrderedTaskTeam orderedTaskTeam) {

		Team team = new Team();
		OrderedTask orderedTask = new OrderedTask();

		team = teamRepository.findById(orderedTaskTeam.getTeam().getId()).get();
		orderedTask = orderedTaskRepository.findById(orderedTaskTeam.getOrderedTask().getOrderTaskId()).get();

		orderedTaskTeam.setTeam(team);
		orderedTaskTeam.setOrderedTask(orderedTask);

		orderedTaskTeamRepository.save(orderedTaskTeam);
	}
}
