package com.nttdata.service.impl;

import java.sql.Timestamp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.persistence.entities.Employee;
import com.nttdata.persistence.repository.EmployeeRepository;
import com.nttdata.service.EmployeeService;
import com.nttdata.service.dto.EmployeeAddDTO;
import com.nttdata.service.dto.EmployeeDTO;
import com.nttdata.service.dto.EmployeeUpdateDTO;
import com.nttdata.service.mappers.EmployeeMapper;
import com.nttdata.util.HashPassword;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public EmployeeDTO getEmployee(long id) {

		Optional<Employee> employee = employeeRepository.findById(id);

		if (employee.isPresent()) {

			EmployeeDTO employeesDTO = employeeMapper.employeeToEmployeeDTO(employee.get());

			return employeesDTO;
		}

		return null;
	}

	@Override
	public boolean updateEmployee(long id, EmployeeUpdateDTO employeeDTO) {

		Optional<Employee> employee = employeeRepository.findById(id);

		if (employee.isPresent()) {

			Employee employeeToUpdate = employee.get();

			if (employeeDTO.getName() != null)
				employeeToUpdate.setName(employeeDTO.getName());
			if (employeeDTO.getSurname1() != null)
				employeeToUpdate.setSurname1(employeeDTO.getSurname1());
			if (employeeDTO.getSurname2() != null)
				employeeToUpdate.setSurname2(employeeDTO.getSurname2());
			if (employeeDTO.getEmail() != null)
				employeeToUpdate.setEmail(employeeDTO.getEmail());
			if (employeeDTO.getPhone_number() != null)
				employeeToUpdate.setPhone_number(employeeDTO.getPhone_number());
			if (employeeDTO.getNif() != null) {
				// Employed with this NIF already exists:
				if (employeeRepository.findByNIF(employeeDTO.getNif()) != null)
					return false;

				employeeToUpdate.setNif(employeeDTO.getNif());
			}

			if (employeeDTO.getNickname() != null)
				employeeToUpdate.setNickname(employeeDTO.getNickname());
			if (employeeDTO.getPassword() != null) {
				// Hash password:
				String hashedPassword = HashPassword.hashPassword(employeeDTO.getPassword());
				employeeToUpdate.setPassword(hashedPassword);
			}

			employeeToUpdate.setModified_date(new Timestamp(System.currentTimeMillis()));

			employeeRepository.save(employeeToUpdate);

			return true;
		}

		return false;
	}

	@Override
	public boolean deleteEmployee(long id) {

		Optional<Employee> employee = employeeRepository.findById(id);

		if (employee.isPresent()) {
			Employee employeeToDelete = employee.get();

			if (employeeToDelete.getStatus() != "ELIMINADO") {
				employeeToDelete.setStatus("ELIMINADO");
				employeeToDelete.setCancel_date(new Timestamp(System.currentTimeMillis()));

				employeeRepository.save(employeeToDelete);

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean addEmployee(EmployeeAddDTO employeeNew) {

		Employee employeeToAdd = employeeMapper.addEmployeeMapper(employeeNew);

		employeeToAdd.setId(employeeRepository.count() + 1);

		// Employed with this NIF already exists:
		if (employeeRepository.findByNIF(employeeNew.getNif()) != null)
			return false;

		// Hash password:
		String hashedPassword = HashPassword.hashPassword(employeeToAdd.getPassword());
		employeeToAdd.setPassword(hashedPassword);

		employeeToAdd.setStatus("ACTIVO");
		employeeToAdd.setEntry_date(new Timestamp(System.currentTimeMillis()));

		employeeRepository.save(employeeToAdd);

		return true;
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {

		List<Employee> employeesActive = employeeRepository.findAllActive();

		List<EmployeeDTO> employeesDTO = employeeMapper.getAllEmployeesMapper(employeesActive);

		return employeesDTO;
	}

}
