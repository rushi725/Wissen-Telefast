package com.telefast.sfs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.Team;
import com.telefast.sfs.repository.EmployeeRepository;

@Repository
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Transactional
	@Override
	public List<Employee> allAvailableEmployees(Team team) {
		int id = team.getId();
		return employeeRepository.findAllByTeamId(id);
		}

}
