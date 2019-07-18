package com.telefast.sfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	@Query("from Project p where p.projectManager.employeeId=?1")
	List<Project> findProjectsByManagerId(int managerId);

}
