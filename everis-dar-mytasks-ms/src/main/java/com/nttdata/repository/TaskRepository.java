package com.nttdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nttdata.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Query(value = "from Tasks where status != 3")
	List<Task> findAllActive();

}
