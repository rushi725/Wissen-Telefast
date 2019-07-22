package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("FROM User us WHERE userName=?1")
	User findByUserName(String username);
}
