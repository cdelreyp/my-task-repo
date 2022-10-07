package com.nttdata.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.repository.TaskRepository;
import com.nttdata.repository.entities.Task;
import com.nttdata.service.TaskService;
import com.nttdata.service.dto.TaskAddDTO;
import com.nttdata.service.dto.TaskDTO;
import com.nttdata.service.dto.TaskUpdateDTO;
import com.nttdata.service.mappers.TaskMapper;
import com.nttdata.utils.enums.enumStatus;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskMapper taskMapper;

	public ArrayList<TaskDTO> getAllTasks() {

		List<Task> tasks = taskRepository.findAll();

		ArrayList<TaskDTO> tasksDTO = (ArrayList<TaskDTO>) taskMapper.getAllTasksMapper(tasks);

		return tasksDTO;
	}

	public ArrayList<TaskDTO> getAllByStatus(enumStatus status) {

		List<Task> tasks = taskRepository.findAll().stream().filter(p -> p.getStatus() == status)
				.collect(Collectors.toList());

		ArrayList<TaskDTO> tasksDTO = (ArrayList<TaskDTO>) taskMapper.getAllTasksMapper(tasks);

		return tasksDTO;
	}

	public Task getTaskById(Long id) {

		Optional<Task> task = taskRepository.findById(id);

		if (task.isPresent()) {

			return task.get();
		}

		return null;
	}

	public Task addTask(TaskAddDTO task) {

		Task taskToAdd = new Task();

		taskToAdd.setDescription(task.getDescription());
		taskToAdd.setStatus(enumStatus.IN_PROGRESS);

		taskToAdd.setEntry_date(new Timestamp(System.currentTimeMillis()));
		taskToAdd.setModified_date(taskToAdd.getEntry_date());

		taskRepository.save(taskToAdd);

		return taskToAdd;
	}

	public Task updateTask(TaskUpdateDTO task, Long id) {

		Optional<Task> taskToUpdate = taskRepository.findById(id);

		if (taskToUpdate.isPresent() && !taskToUpdate.get().getStatus().equals(enumStatus.DELETED)) {

			if (task.getDescription() != null) {
				taskToUpdate.get().setDescription(task.getDescription());
				taskToUpdate.get().setModified_date(new Timestamp(System.currentTimeMillis()));
			}
			if (task.getStatus() != null) {
				taskToUpdate.get().setStatus(task.getStatus());
				taskToUpdate.get().setModified_date(new Timestamp(System.currentTimeMillis()));
			}

			taskRepository.save(taskToUpdate.get());

			return taskToUpdate.get();
		}

		return null;
	}

	public void deleteTask(Long id) {

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