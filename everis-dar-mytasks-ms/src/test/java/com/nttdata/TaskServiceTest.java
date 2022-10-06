package com.nttdata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.nttdata.model.Task;
import com.nttdata.repository.TaskRepository;
import com.nttdata.service.TaskService;
import com.nttdata.service.dto.TaskAddDTO;
import com.nttdata.service.dto.TaskDTO;
import com.nttdata.service.dto.TaskUpdateDTO;

import utils.enumStatus;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TaskServiceTest {
	private final static String DESC_1 = "Task 1";
	private final static String DESC_2 = "Task 2";
	
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskService taskService;
	
	@BeforeEach
	void initDB() {
		createTestTask(DESC_1);
	}
	
	private void createTestTask(String description) {
		Task taskToCreate = new Task();
		
		taskToCreate.setId(taskRepository.count());
		
		taskToCreate.setDescription(description);
		taskToCreate.setStatus(enumStatus.IN_PROGRESS);
		taskToCreate.setEntry_date(new Timestamp(System.currentTimeMillis()));
		taskToCreate.setModified_date(taskToCreate.getEntry_date());
		taskRepository.save(taskToCreate);
	}
	
	
	
	
	@Test
	@Order(1)
	void getTaskByIdTest() {
		Task task = taskService.getTaskById(1L);
		assertNotNull(task);
		assertEquals(task.getId(),(Long)1L);
		assertEquals(task.getDescription(),DESC_1);
		
	}
	
	
	@Test
	@Order(2)
	void getAllTest() {
		ArrayList<TaskDTO> tasks = taskService.getAllTasks();
		assertEquals(tasks.size(),1);
	}
	
	@Test
	@Order(3)
	void createTaskTest() {
		TaskAddDTO task = new TaskAddDTO();
		task.setDescription(DESC_2);

		Task task2 = taskService.addTask(task);
		assertNotNull(task2);
		assertEquals(task2.getId(),(Long)2L);
		assertEquals(task2.getDescription(),DESC_2);
		assertEquals(task2.getStatus(),enumStatus.IN_PROGRESS);
	}
	
	
	@Test
	@Order(4)
	void updateNonExistingTaskTest() {
		assertNull(taskService.updateTask(null, -1L));
	}
	
	@Test
	@Order(5)
	void updateDescriptionTaskTest() {
		TaskUpdateDTO task = new TaskUpdateDTO();
		task.setDescription(DESC_2);
		Task taskUpdated = taskService.updateTask(task, 1L);
		assertEquals(DESC_2,taskUpdated.getDescription());
	}
	
	/*@Test
	void updateStatusNonDeletedTaskTest() {
		assertEquals(2,2);
	}
	
	
	@Test
	void updateStatusDeletedTaskTest() {
		assertEquals(2,2);
	}
	
	
	@Test
	void deleteNonExistingTaskTest() {
		assertEquals(2,2);
	}
	
	@Test
	void deleteTaskTest() {
		assertEquals(2,2);
	}
	
	
	@Test
	void deleteAlreadyDeletedTaskTest() {
		assertEquals(2,2);
	}*/
}
