package com.nttdata.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.controller.TaskController;
import com.nttdata.repository.entities.Task;
import com.nttdata.service.TaskService;
import com.nttdata.service.dto.TaskAddDTO;
import com.nttdata.service.dto.TaskDTO;
import com.nttdata.service.dto.TaskUpdateDTO;
import com.nttdata.utils.enums.enumStatus;

@RestController
public class TaskControllerImpl implements TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;


	/**
	 * GET TASKS IN DB
	 * CAN BE FILTERED BY STATUS AND USER THAT CREATED IT
	 * 
	 * @param status path variable (optional)
	 * @param userCreator path variable (optional)
	 * @return all Tasks with status (JSON Array), 200 OK
	 */
	
	@Override
	public ResponseEntity<?> getTasks(enumStatus status, String userCreator) {
		logger.info("Getting all tasks");
		return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks(status,userCreator));
	}

	/**
	 * GET BY KEY OPERATION
	 * 
	 * @param id path variable (id of the Task to retrieve)
	 * @return Task that matches the key (JSON), 200 OK or 404 NOT FOUND
	 */
	@Override
	public ResponseEntity<?> getTask(long id) {
		if (taskService.getTaskById(id) == null) {
			logger.info("Task with id:" + id + " not found when trying to obtain");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			logger.info("Geting info from task with id:" + id);
			return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
		}
	}

	/**
	 * POST OPERATION
	 * 
	 * @param task request body (Task to insert)
	 * @return inserted Task (JSON), 201 CREATED (or 409 CONFLICT %si tenemos algun
	 *         unique%)
	 */
	@Override
	public ResponseEntity<?> addTask(TaskAddDTO task) {
		Task taskCreated = this.taskService.addTask(task);
		logger.info("Adding new task with id:" + taskCreated.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
	}

	/**
	 * PUT OPERATION
	 * 
	 * @param task request body (Task to update)
	 * @param id   path variable (id of the Task to update)
	 * @return updated Task (JSON), 200 OK or 404 NOT FOUND
	 */
	@Override
	public ResponseEntity<?> updateTask(long id, TaskUpdateDTO task) {
		if (taskService.getTaskById(id) == null) {
			logger.info("Task with id:" + id + " not found when trying to modify");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			Task taskModified = this.taskService.updateTask(task, id);
			logger.info("Modifying task with id:" + id);
			return ResponseEntity.status(HttpStatus.OK).body(taskModified);
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
		if (taskService.getTaskById(id) == null) {
			logger.info("Task with id:" + id + " not found when trying to delete");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			this.taskService.deleteTask(id);
			logger.info("Deleting task with id:" + id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}

}
