package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer>{

}
