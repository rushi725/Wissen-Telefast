package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

}
