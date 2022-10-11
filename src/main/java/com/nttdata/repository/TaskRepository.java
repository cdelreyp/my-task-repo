package com.nttdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.repository.entities.Task;

/**
 * Class that implements the Repository pattern (via JPA) in order to connect to
 * the H2 Database
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
