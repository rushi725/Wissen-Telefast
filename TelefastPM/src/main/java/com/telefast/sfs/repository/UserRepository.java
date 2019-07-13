package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
