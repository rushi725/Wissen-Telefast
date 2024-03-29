package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.OrderedTaskTeam;

@Repository
public interface OredredTaskTeamRepository extends JpaRepository<OrderedTaskTeam, Integer> {

}
