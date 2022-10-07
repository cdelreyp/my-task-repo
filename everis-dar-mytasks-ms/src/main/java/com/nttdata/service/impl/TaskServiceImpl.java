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

	public ArrayList<TaskDTO> getAllTasks(enumStatus status, String userCreator) {

		List<Task> taskList=taskRepository.findAll();
		if(status!=null) {
			//Filter by status
			taskList = taskList.stream().filter(p -> p.getStatus() == status)
					.collect(Collectors.toList());
		}
		if(userCreator!=null) {
			//Filter by userCreator
			taskList = taskList.stream().filter(p -> p.getUserCreator().equals(userCreator))
					.collect(Collectors.toList());
		}

		return (ArrayList<TaskDTO>) taskMapper.getAllTasksMapper(taskList);
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

		taskToAdd.setUserCreator(task.getUserCreator());
		taskToAdd.setUserAsigned(task.getUserAsigned());
		
		taskToAdd.setEntryDate(new Timestamp(System.currentTimeMillis()));
		taskToAdd.setModifiedDate(taskToAdd.getEntryDate());

		taskRepository.save(taskToAdd);

		return taskToAdd;
	}

	public Task updateTask(TaskUpdateDTO task, Long id) {

		Optional<Task> taskToUpdate = taskRepository.findById(id);

		if (taskToUpdate.isPresent() &&  !taskToUpdate.get().getStatus().equals(enumStatus.DELETED)) {

			if (task.getDescription() != null) {
				taskToUpdate.get().setDescription(task.getDescription());
				taskToUpdate.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
			}
			if (task.getStatus() != null) {
				taskToUpdate.get().setStatus(task.getStatus());
				taskToUpdate.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
			}
			if (task.getUserAsigned() != null) {
				taskToUpdate.get().setUserAsigned(task.getUserAsigned());
				taskToUpdate.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
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
				taskToDelete.setCancelDate(new Timestamp(System.currentTimeMillis()));

				taskRepository.save(taskToDelete);
			}

		}

	}
}