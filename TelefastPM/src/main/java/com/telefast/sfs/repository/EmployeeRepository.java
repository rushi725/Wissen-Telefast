package com.telefast.sfs.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query("From Employee emp where emp.team.teamId=?1 and emp.availableStatus=true")
	List<Employee> findAllByTeamId(int teamId);


	@Query("from Employee emp where emp.employeeId=?1 and emp.empRole=com.telefast.sfs.model.EmpRole.ROLE_TEAM_MANAGER")
	Employee getTeamByManagerId(int managerId);
		
	@Query("from Employee emp where emp.team.teamId=?1 and emp.empRole=com.telefast.sfs.model.EmpRole.ROLE_TEAM_MANAGER")
	Employee getTeamManagerByTeamId(int teamId);

}
