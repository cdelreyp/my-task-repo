package com.nttdata.service.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nttdata.persistence.entities.Employee;
import com.nttdata.service.dto.EmployeeAddDTO;
import com.nttdata.service.dto.EmployeeDTO;

@Component
public class EmployeeMapper {

	public List<EmployeeDTO> getAllEmployeesMapper(List<Employee> employees) {

		List<EmployeeDTO> employeesDTO = new ArrayList<>();

		if (!employees.isEmpty()) {
			employees.forEach(employee -> {
				EmployeeDTO employeeDTO = new EmployeeDTO();

				employeeDTO.setId(employee.getId());
				employeeDTO.setName(employee.getName());
				employeeDTO.setSurname1(employee.getSurname1());
				employeeDTO.setSurname2(employee.getSurname2());
				employeeDTO.setEmail(employee.getEmail());
				employeeDTO.setPhone_number(employee.getPhone_number());
				employeeDTO.setNif(employee.getNif());
				employeeDTO.setNickname(employee.getNickname());
				employeeDTO.setStatus(employee.getStatus());

				employeesDTO.add(employeeDTO);
			});
		}
		return employeesDTO;
	}
	
	public Employee addEmployeeMapper(EmployeeAddDTO employeeDTO) {

		Employee employee = new Employee();
	
		employee.setName(employeeDTO.getName());
		employee.setSurname1(employeeDTO.getSurname1());
		employee.setSurname2(employeeDTO.getSurname2());
		employee.setEmail(employeeDTO.getEmail());
		employee.setPhone_number(employeeDTO.getPhone_number());
		employee.setNif(employeeDTO.getNif());
		employee.setNickname(employeeDTO.getNickname());
		employee.setPassword(employeeDTO.getPassword());
		
		return employee;
	}
	
	public EmployeeDTO employeeToEmployeeDTO(Employee employee) {

		EmployeeDTO employeeDTO = new EmployeeDTO();
	
		employeeDTO.setId(employee.getId());
		employeeDTO.setName(employee.getName());
		employeeDTO.setSurname1(employee.getSurname1());
		employeeDTO.setSurname2(employee.getSurname2());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setPhone_number(employee.getPhone_number());
		employeeDTO.setNif(employee.getNif());
		employeeDTO.setNickname(employee.getNickname());
		employeeDTO.setStatus(employee.getStatus());
		
		return employeeDTO;
	}

}
