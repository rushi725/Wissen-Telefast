package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedTask;

import antlr.collections.List;

@Repository
public interface OrderedTaskRepository extends JpaRepository<OrderedTask, Integer> {

	@Query("from ORDERED_TASK t where t.employeeId=?1")
	OrderedTask findByEmployeeId(int parseInt);

	
}
