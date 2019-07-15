package com.telefast.sfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.ServiceWorkflow;

@Repository
public interface ServiceWorkflowRepository extends JpaRepository<ServiceWorkflow, Integer> {
	
	@Query("select workFlowId FROM ServiceWorkflow SW WHERE SW.dependentOn=?1 AND SW.SERVICE.SERVICEID=?2 and SW.TEAM.TEAMID=?3")
	List<Integer> findAllIds(int taskId,int serviceId,int teamId);
}
