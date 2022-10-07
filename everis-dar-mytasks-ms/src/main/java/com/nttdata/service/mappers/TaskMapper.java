package com.nttdata.service.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nttdata.repository.entities.Task;
import com.nttdata.service.dto.TaskDTO;

/**
 * Mapper to convert Task entities into TaskDTO objects
 */
@Component
public class TaskMapper {
	
	public List<TaskDTO> getAllTasksMapper(List<Task> tasks) {
		// List of TaskDTO entities to be returned
		List<TaskDTO> tasksDTO = new ArrayList<>();
		
		if (!tasks.isEmpty()) {
			tasks.forEach(task -> {
				TaskDTO taskDTO = new TaskDTO();
				
				// Assign the Task fields to the TaskDTO object
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
