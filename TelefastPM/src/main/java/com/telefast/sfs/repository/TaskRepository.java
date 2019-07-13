package com.telefast.sfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.Task;

@Repository
<<<<<<< HEAD:TelefastPM/src/main/java/com/telefast/sfs/repository/TasksRepository.java
public interface TasksRepository extends JpaRepository<Task, Integer> {
=======
public interface TaskRepository extends JpaRepository<Task, Integer> {
>>>>>>> b885409afacfd94fe9af41ccd8ed7c732a396c19:TelefastPM/src/main/java/com/telefast/sfs/repository/TaskRepository.java

}
