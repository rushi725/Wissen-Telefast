package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.ServiceWorkflow;

@Repository
public interface ServiceWorkflowRepository extends JpaRepository<ServiceWorkflow, Integer> {

}
