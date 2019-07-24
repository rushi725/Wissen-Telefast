package com.telefast.sfs.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.EmpRole;
import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.Team;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query("From Employee emp where emp.team.teamId=?1 and emp.availableStatus=true and emp.empRole=com.telefast.sfs.model.EmpRole.ROLE_TEAM_MEMBER")
	List<Employee> findAllAvailableByTeamId(int teamId);
	
	@Query("From Employee emp where emp.team.teamId=?1 and emp.empRole=com.telefast.sfs.model.EmpRole.ROLE_TEAM_MEMBER")
	List<Employee> findAllByTeamId(int teamId);

	@Query("select emp.team from Employee emp where emp.employeeId=?1 and emp.empRole=com.telefast.sfs.model.EmpRole.ROLE_TEAM_MANAGER")
	Team getTeamByManagerId(int managerId);
		
//	@Query("from Employee emp where emp.team.teamId=?1 and emp.empRole=com.telefast.sfs.model.EmpRole.ROLE_TEAM_MANAGER")
//	Employee getTeamManagerByTeamId(int teamId);

	@Query("select employeeId from Employee emp where emp.empContactNo=?1")
	int getEmpId(String empContactNo);

	
	@Query("from Employee emp where emp.empRole=com.telefast.sfs.model.EmpRole.ROLE_SERVICE_MANAGER")
	List<Employee> findAllServiceManagers();
	
	List<Employee> findByEmpRole(EmpRole empRole);

}
