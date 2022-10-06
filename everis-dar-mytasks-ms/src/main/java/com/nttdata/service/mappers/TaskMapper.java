package com.nttdata.service.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nttdata.model.Task;
import com.nttdata.service.dto.TaskDTO;

@Component
public class TaskMapper {
	
	public List<TaskDTO> getAllTasksMapper(List<Task> tasks) {

		List<TaskDTO> tasksDTO = new ArrayList<>();

		if (!tasks.isEmpty()) {
			tasks.forEach(task -> {
				TaskDTO taskDTO = new TaskDTO();

				taskDTO.setId(task.getId());
				taskDTO.setStatus(task.getStatus());
				taskDTO.setDescription(task.getDescription());
				taskDTO.setEntry_date(task.getEntry_date());
				taskDTO.setModified_date(task.getModified_date());
				taskDTO.setCancel_date(task.getCancel_date());

				tasksDTO.add(taskDTO);
			});
		}
		
		return tasksDTO;
	}

}
