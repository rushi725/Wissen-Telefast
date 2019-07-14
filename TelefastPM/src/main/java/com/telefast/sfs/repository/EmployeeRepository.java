package com.telefast.sfs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
