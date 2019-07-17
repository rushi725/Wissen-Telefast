package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.OrderedService;

@Repository
public interface OrderedServiceRepository extends JpaRepository<OrderedService, Integer> {

}
