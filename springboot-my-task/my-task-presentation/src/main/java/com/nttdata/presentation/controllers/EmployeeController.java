package com.nttdata.presentation.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nttdata.presentation.http.CustomHeaders;
import com.nttdata.service.dto.EmployeeAddDTO;
import com.nttdata.service.dto.EmployeeUpdateDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Employees", description = "API Employees")
public interface EmployeeController {

	@Operation(description = "Get the data of an employee by id.", operationId = "getEmployee", summary = "Get the data of an employee")
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> getEmployee(@PathVariable(value = "id", required = true) long id);

	@Operation(description = "Update the data of an employee by id.", operationId = "updateEmployee", summary = "Update the data of an employee")
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PATCH)
	@ResponseBody
	ResponseEntity<?> updateEmployee(@PathVariable(value = "id", required = true) long id,
			@RequestHeader(value = CustomHeaders.X_REQUEST_ID, required = true) UUID xRequestID,
			@RequestBody EmployeeUpdateDTO employeeDTO);

	@Operation(description = "Delete an employee by id.", operationId = "deleteEmployee", summary = "Delete an employee")
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	ResponseEntity<?> deleteEmployee(@PathVariable(value = "id", required = true) long id,
			@RequestHeader(value = CustomHeaders.X_REQUEST_ID, required = true) UUID xRequestID);

	@Operation(description = "Add an employee.", operationId = "addEmployee", summary = "Add an employee")
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	@ResponseBody
	ResponseEntity<?> addEmployee(@RequestHeader(value = CustomHeaders.X_REQUEST_ID, required = true) UUID xRequestID,
			@RequestBody EmployeeAddDTO employeeDTO);

	@Operation(description = "Get the data of all employees.", operationId = "getAllEmployees", summary = "Get the data of all employees")
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> getAllEmployees(
			@RequestHeader(value = CustomHeaders.X_REQUEST_ID, required = true) UUID xRequestID);

}
