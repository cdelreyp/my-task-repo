package com.nttdata.presentation.controllers.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.nttdata.presentation.controllers.EmployeeController;
import com.nttdata.service.EmployeeService;
import com.nttdata.service.dto.EmployeeAddDTO;
import com.nttdata.service.dto.EmployeeDTO;
import com.nttdata.service.dto.EmployeeUpdateDTO;
import com.nttdata.util.exception.ErrorMessage;
import com.nttdata.util.exception.TypeErrorEnum;

@Controller
public class EmployeeControllerImpl implements EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<?> getEmployee(long id) {

		EmployeeDTO employeeDTO = employeeService.getEmployee(id);

		if (employeeDTO == null) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setCode("404");
			errorMessage.setDescription("Employee with specified ID not found.");
			errorMessage.setMessage("NOT FOUND");
			errorMessage.setType(TypeErrorEnum.ERROR);
			return new ResponseEntity<ErrorMessage>(errorMessage, null, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(employeeDTO);
		}
	}

	@Override
	public ResponseEntity<?> updateEmployee(long id, UUID xRequestID, EmployeeUpdateDTO employeeDTO) {

		boolean isUpdated = employeeService.updateEmployee(id, employeeDTO);

		if (isUpdated) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setCode("202");
			errorMessage.setDescription("Employee update successful.");
			errorMessage.setMessage("CREATED");
			errorMessage.setType(TypeErrorEnum.INFO);
			return new ResponseEntity<ErrorMessage>(errorMessage, null, HttpStatus.CREATED);
		} else {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setCode("404");
			errorMessage.setDescription("Employee with specified ID not found.");
			errorMessage.setMessage("NOT FOUND");
			errorMessage.setType(TypeErrorEnum.ERROR);
			return new ResponseEntity<ErrorMessage>(errorMessage, null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> deleteEmployee(long id, UUID xRequestID) {

		boolean isDeleted = employeeService.deleteEmployee(id);

		if (isDeleted) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setCode("200");
			errorMessage.setDescription("Employee delete successful.");
			errorMessage.setMessage("OK");
			errorMessage.setType(TypeErrorEnum.INFO);
			return new ResponseEntity<ErrorMessage>(errorMessage, null, HttpStatus.OK);
		} else {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setCode("404");
			errorMessage.setDescription("Employee with specified ID not found.");
			errorMessage.setMessage("NOT FOUND");
			errorMessage.setType(TypeErrorEnum.ERROR);
			return new ResponseEntity<ErrorMessage>(errorMessage, null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> addEmployee(UUID xRequestID, EmployeeAddDTO employeeDTO) {

		boolean isAdded = employeeService.addEmployee(employeeDTO);

		if (isAdded) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setCode("202");
			errorMessage.setDescription("Employee added successful.");
			errorMessage.setMessage("CREATED");
			errorMessage.setType(TypeErrorEnum.INFO);
			return new ResponseEntity<ErrorMessage>(errorMessage, null, HttpStatus.CREATED);
		} else {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setCode("404");
			errorMessage.setDescription("Employee could not be added.");
			errorMessage.setMessage("NOT FOUND");
			errorMessage.setType(TypeErrorEnum.ERROR);
			return new ResponseEntity<ErrorMessage>(errorMessage, null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> getAllEmployees(UUID xRequestID) {

		List<EmployeeDTO> employees = employeeService.getAllEmployees();

		return ResponseEntity.ok(employees);
	}

}
