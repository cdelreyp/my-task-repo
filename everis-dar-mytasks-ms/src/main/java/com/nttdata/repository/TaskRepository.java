package com.nttdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.repository.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
