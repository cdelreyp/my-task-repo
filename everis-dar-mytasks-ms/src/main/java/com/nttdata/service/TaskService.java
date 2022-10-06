package com.nttdata.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Task;
import com.nttdata.repository.TaskRepository;
import com.nttdata.service.dto.TaskDTO;
import com.nttdata.service.mappers.TaskMapper;

import utils.enumStatus;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskMapper taskMapper;

	public Task getTaskById(Long id) {

		Optional<Task> task = taskRepository.findById(id);

		if (task.isPresent()) {

			return task.get();
		}

		return null;
	}

	public ArrayList<TaskDTO> getAll() {

		List<Task> tasks = taskRepository.findAll();

		ArrayList<TaskDTO> tasksDTO = (ArrayList<TaskDTO>) taskMapper.getAllTasksMapper(tasks);

		return tasksDTO;
	}

	public Task createTask(Task task) {

		Task taskToCreate = new Task();
		
		taskToCreate.setId(taskRepository.count() - 1);
		
		taskToCreate.setDescription(task.getDescription());
		taskToCreate.setStatus(enumStatus.IN_PROGRESS);

		taskToCreate.setEntry_date(new Timestamp(System.currentTimeMillis()));
		taskToCreate.setModified_date(taskToCreate.getEntry_date());

		taskRepository.save(taskToCreate);

		return taskToCreate;
	}

	public Task updateTask(Task task, Long id) {

		Optional<Task> taskToUpdate = taskRepository.findById(id);

		if (taskToUpdate.isPresent()) {

			if (task.getDescription() != null) {
				taskToUpdate.get().setDescription(task.getDescription());
				taskToUpdate.get().setModified_date(new Timestamp(System.currentTimeMillis()));
			}
			if (task.getStatus() != null && !task.getStatus().equals(enumStatus.DELETED)) {
				taskToUpdate.get().setStatus(task.getStatus());
				taskToUpdate.get().setModified_date(new Timestamp(System.currentTimeMillis()));
			}

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
