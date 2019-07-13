package com.telefast.sfs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedTask;

@Repository
public interface OrderedTaskRepository extends JpaRepository<OrderedTask, Integer> {


}
