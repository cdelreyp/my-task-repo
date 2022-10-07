package com.nttdata.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.nttdata.repository.entities.Task;
import com.nttdata.service.dto.TaskAddDTO;
import com.nttdata.service.dto.TaskDTO;
import com.nttdata.service.dto.TaskUpdateDTO;
import com.nttdata.utils.enums.enumStatus;

@Service
public interface TaskService {

	ArrayList<TaskDTO> getAllTasks(enumStatus status, String userCreator);

	Task getTaskById(Long id);

	Task addTask(TaskAddDTO task);

	Task updateTask(TaskUpdateDTO task, Long id);

	void deleteTask(Long id);

}