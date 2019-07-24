package com.telefast.sfs.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.model.Task;

@Repository
public interface OrderedTaskRepository extends JpaRepository<OrderedTask, Integer> {
//	
	@Query("from OrderedTask o where o.orderedService.orderedServiceId=?1 and o.task.taskId=?2")
	OrderedTask findOrderTaskId(int orderServiceId, int taskId);

	@Query("from OrderedTask o where o.employee.employeeId=?1")
	List<OrderedTask> findByEmployeeId(int employeeId);

//	@Query("select o.task from OrderedTask o where o.task.taskId=?1")
//	OrderedTask findTaskbyTaskId(int taskId);

	@Query("from OrderedTask o where o.task.taskId in :taskIds")
	List<OrderedTask> findAllOrderedTaskByTaskId(@Param("taskIds") List<Integer> taskIds);
	
//	@Query("from OrderedTask o where o.task.taskId=?1")
//	OrderedTask findOrderTaskId(int taskId);
		
	List<OrderedTask> findByOrderedService(OrderedService orderedService);


}
