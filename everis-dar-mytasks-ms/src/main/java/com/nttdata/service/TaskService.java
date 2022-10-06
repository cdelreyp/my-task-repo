package com.nttdata.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Task;
import com.nttdata.repository.TaskRepository;

import utils.enumStatus;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task getTaskById(Long id) {

		Optional<Task> task = taskRepository.findById(id);

		if (task.isPresent()) {

			return task.get();
		}

		return null;
	}

	public ArrayList<Task> getAll() {

		ArrayList<Task> tasks = (ArrayList<Task>) taskRepository.findAll();

		return tasks;
	}

	public Task createTask(Task task) {

		Task taskToCreate = new Task();

		if (task.getDescription() != null) {
			taskToCreate.setDescription(task.getDescription());
		}
		if (task.getStatus() != null) {
			taskToCreate.setStatus(task.getStatus());
		}

		taskToCreate.setEntry_date(new Timestamp(System.currentTimeMillis()));

		taskRepository.save(taskToCreate);

		return taskToCreate;
	}

	public Task updateTask(Task task, Long id) {

		Optional<Task> taskToUpdate = taskRepository.findById(id);

		if (taskToUpdate.isPresent()) {

			if (task.getDescription() != null) {
				taskToUpdate.get().setDescription(task.getDescription());
			}
			if (task.getStatus() != null) {
				taskToUpdate.get().setStatus(task.getStatus());
			}

			taskToUpdate.get().setModified_date(new Timestamp(System.currentTimeMillis()));

			taskRepository.save(taskToUpdate.get());

			return taskToUpdate.get();
		}

		return null;
	}

	public void delete(Long id) {

		Optional<Task> task = taskRepository.findById(id);

		if (task.isPresent()) {

			Task taskToDelete = task.get();

			if (taskToDelete.getStatus() != enumStatus.DELETED) {
				taskToDelete.setStatus(enumStatus.DELETED);
				taskToDelete.setCancel_date(new Timestamp(System.currentTimeMillis()));

				taskRepository.save(taskToDelete);
			}

		}

	}
}