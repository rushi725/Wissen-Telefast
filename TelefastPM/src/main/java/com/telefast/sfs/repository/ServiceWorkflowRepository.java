package com.telefast.sfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.ServiceWorkflow;
import com.telefast.sfs.model.Task;

@Repository
public interface ServiceWorkflowRepository extends JpaRepository<ServiceWorkflow, Integer> {
	
	@Query("from ServiceWorkflow s where s.task.getId()=?1 and s.service.getId()=?2")
	List<ServiceWorkflow> findChildrenIds(int taskId,int serviceId);

	//	@Query("select workFlowId FROM ServiceWorkflow SW WHERE SW.dependentOn=?1 AND SW.SERVICE.SERVICEID=?2 and SW.TEAM.TEAMID=?3")
//	List<Integer> findAllIds(int taskId,int serviceId,int teamId);
	
	@Query("from ServiceWorkflow s where s.task.taskId=?1 and s.service.serviceId=?2")
	ServiceWorkflow getByTaskAndService(int taskId, int serviceId);

	@Query(" select s.task from ServiceWorkflow s where s.team.teamId=?1")
	List<Task> findTasksByTeamId(int teamId);

	@Query("select s.task from ServiceWorkflow s where s.service.serviceId=?1")
	List<Task> findTasksByServiceId(int serviceId);


}
