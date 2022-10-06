package com.nttdata.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.service.dto.TaskAddDTO;
import com.nttdata.service.dto.TaskUpdateDTO;
import com.nttdata.utils.enums.enumStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Tasks", description = "API Tasks")
@RestController
public interface TaskController {

	@Operation(description = "description", operationId = "getAllTasks", summary = "summary")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks", produces = "application/json")
	@ResponseBody
	ResponseEntity<?> getAllTasks();

	@Operation(description = "description", operationId = "getTasksByStatus", summary = "summary")
	@RequestMapping(method = RequestMethod.GET, value = "/tasksByStatus", produces = "application/json")
	@ResponseBody
	ResponseEntity<?> getTasksByStatus(@RequestParam(value = "status", required = true) enumStatus status);

	@Operation(description = "description", operationId = "getTask", summary = "summary")
	@RequestMapping(method = RequestMethod.GET, value = "/task/{id}", produces = "application/json")
	@ResponseBody
	ResponseEntity<?> getTask(@PathVariable(value = "id", required = true) long id);

	@Operation(description = "description", operationId = "addTask", summary = "summary")
	@RequestMapping(method = RequestMethod.POST, value = "/task", consumes = "application/json")
	@ResponseBody
	ResponseEntity<?> addTask(@RequestBody TaskAddDTO task);

	@Operation(description = "description", operationId = "updateTask", summary = "summary")
	@RequestMapping(method = RequestMethod.PUT, value = "/task/{id}", consumes = "application/json")
	@ResponseBody
	ResponseEntity<?> updateTask(@PathVariable(value = "id", required = true) long id, @RequestBody TaskUpdateDTO task);

	@Operation(description = "description", operationId = "deleteTask", summary = "summary")
	@RequestMapping(method = RequestMethod.DELETE, value = "/task/{id}", produces = "application/json")
	@ResponseBody
	ResponseEntity<?> deleteTask(@PathVariable(value = "id", required = true) long id);

}
