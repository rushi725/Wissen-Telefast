package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
