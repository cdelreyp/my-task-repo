package com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.nttdata.model.Task;
import com.nttdata.service.TaskService;

@Controller
public class TaskControllerImpl implements TaskControllerI {

	@Autowired
	private TaskService taskService;

	/**
	 * GET OPERATION
	 * 
	 * @return all Tasks (JSON Array), 200 OK
	 */
	@Override
	public ResponseEntity<?> getAllTasks() {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.getAll());
	}

	/**
	 * GET BY KEY OPERATION
	 * 
	 * @param id path variable (id of the Task to retrieve)
	 * @return Task that matches the key (JSON), 200 OK or 404 NOT FOUND
	 */
	@Override
	public ResponseEntity<?> getTask(long id) {
		if (taskService.getTaskById(id) == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
	}

	/**
	 * POST OPERATION
	 * 
	 * @param task request body (Task to insert)
	 * @return inserted Task (JSON), 201 CREATED or 409 CONFLICT
	 */
	@Override
	public ResponseEntity<?> addTask(Task task) {
		this.taskService.createTask(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(task);
	}

	/**
	 * PUT OPERATION
	 * 
	 * @param task request body (Task to update)
	 * @param id   path variable (id of the Task to update)
	 * @return updated Task (JSON), 200 OK or 404 NOT FOUND
	 */
	@Override
	public ResponseEntity<?> updateTask(long id, Task task) {
		if (taskService.getTaskById(task.getId()) == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		else {
			this.taskService.updateTask(task, id);
			return ResponseEntity.status(HttpStatus.OK).body(task);
		}
	}

	/**
	 * DELETE OPERATION
	 * 
	 * @param id path variable (id of the Task to delete)
	 * @return empty response body, 204 NO CONTENT or 404 NOT FOUND
	 */
	@Override
	public ResponseEntity<?> deleteTask(long id) {
		if (taskService.getTaskById(id) == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		else {
			this.taskService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}

}
