package com.nttdata.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.Task;
import com.nttdata.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "API Tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	/**
	 * GET OPERATION
	 * 
	 * @return all Tasks (JSON Array), 200 OK
	 */
	@GetMapping(produces = "application/json")
	@Operation(description = "description", operationId = "get", summary = "summary")
	public ResponseEntity<ArrayList<Task>> get() {
		logger.info("Getting all tasks");
		return ResponseEntity.status(HttpStatus.OK).body(taskService.getAll());
	}

	/**
	 * POST OPERATION
	 * 
	 * @param task request body (Task to insert)
	 * @return inserted Task (JSON), 201 CREATED or 409 CONFLICT
	 */
	@PostMapping(consumes = "application/json")
	@Operation(description = "description", operationId = "get", summary = "summary")
	public ResponseEntity<Task> post(@RequestBody Task task) {
		this.taskService.createTask(task);
		logger.info("Adding new task");
		return ResponseEntity.status(HttpStatus.CREATED).body(task);
	}

	/**
	 * PUT OPERATION
	 * 
	 * @param task request body (Task to update)
	 * @param id   path variable (id of the Task to update)
	 * @return updated Task (JSON), 200 OK or 404 NOT FOUND
	 */
	@PutMapping(value = "{id}", consumes = "application/json")
	@Operation(description = "description", operationId = "get", summary = "summary")
	public ResponseEntity<Task> put(@RequestBody Task task, @PathVariable("id") Long id) {
		if (taskService.getTaskById(task.getId()) == null) {
			logger.info("Task with id:" + task.getId() + " not found when trying to modify");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			this.taskService.updateTask(task, id);
			logger.info("Modifying task with id:" + id);
			return ResponseEntity.status(HttpStatus.OK).body(task);
		}
	}

	/**
	 * DELETE OPERATION
	 * 
	 * @param id path variable (id of the Task to delete)
	 * @return empty response body, 204 NO CONTENT or 404 NOT FOUND
	 */
	@DeleteMapping("{id}")
	@Operation(description = "description", operationId = "get", summary = "summary")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (taskService.getTaskById(id) == null) {
			logger.info("Task with id:" + id + " not found when trying to delete");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			this.taskService.delete(id);
			logger.info("Deleting task with id:" + id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}

	/**
	 * GET BY KEY OPERATION
	 * 
	 * @param id path variable (id of the Task to retrieve)
	 * @return Task that matches the key (JSON), 200 OK or 404 NOT FOUND
	 */
	@GetMapping(value = "{id}", produces = "application/json")
	@Operation(description = "description", operationId = "get", summary = "summary")
	public ResponseEntity<Task> getByKey(@PathVariable("id") Long id) {
		if (taskService.getTaskById(id) == null) {
			logger.info("Task with id:" + id + " not found when trying to obtain");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			logger.info("Geting info from task with id:" + id);
			return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
		}
	}

}
