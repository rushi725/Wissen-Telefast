package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Task;

@Repository
public interface TaksRepository extends JpaRepository<Task, Integer> {

}
