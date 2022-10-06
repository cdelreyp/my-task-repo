package com.nttdata.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nttdata.persistence.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "from Employee where status = 'ACTIVO'")
	List<Employee> findAllActive();
	
	@Query(value = "from Employee where nif = :nif")
	Employee findByNIF(String nif);

}
