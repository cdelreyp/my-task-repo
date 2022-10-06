package com.nttdata.service;

import java.util.List;

import com.nttdata.service.dto.EmployeeAddDTO;
import com.nttdata.service.dto.EmployeeDTO;
import com.nttdata.service.dto.EmployeeUpdateDTO;

public interface EmployeeService {

	EmployeeDTO getEmployee(long id);

	boolean updateEmployee(long id, EmployeeUpdateDTO employeeDTO);

	boolean deleteEmployee(long id);

	boolean addEmployee(EmployeeAddDTO employeeDTO);

	List<EmployeeDTO> getAllEmployees();

}
