package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Task;
import com.telefast.sfs.model.Team;

@Repository
public interface OrderedTaskRepository extends JpaRepository<OrderedTask, Integer> {
//	
	@Query("from OrderedTask o where o.orderedService.orderedServiceId=?1 and o.task.taskId=?2")
	OrderedTask findOrderTaskId(int orderServiceId,int taskId);

	@Query("from OrderedTask o where o.employee.employeeId=?1")
	OrderedTask findByEmployeeId(int employeeId);

	@Query("select o.task from OrderedTask o where o.task.taskId=?1")
	OrderedTask findTaskbyTaskId(int taskId);
	
//	@Query("from OrderedTask o where o.task.taskId=?1")
//	OrderedTask findOrderTaskId(int taskId);
	
	//

}
