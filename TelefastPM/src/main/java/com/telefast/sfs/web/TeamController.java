package com.telefast.sfs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.TeamRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sfs/teams")
public class TeamController {
	
	@Autowired
	TeamRepository teamRepository;
	
	@GetMapping
	public List<Team> getTeam(){
		return teamRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> addTeam(@RequestBody Team team) {
		return new ResponseEntity<>(teamRepository.save(team), HttpStatus.CREATED);
	}

}
