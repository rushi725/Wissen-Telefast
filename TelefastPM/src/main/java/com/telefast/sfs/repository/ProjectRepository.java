package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
