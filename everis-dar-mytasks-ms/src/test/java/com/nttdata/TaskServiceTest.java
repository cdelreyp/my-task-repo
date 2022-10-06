package com.nttdata;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nttdata.model.Task;
import com.nttdata.repository.TaskRepository;
import com.nttdata.service.TaskService;

import utils.enumStatus;


@SpringBootTest
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
		createTestTask(DESC_2);
	}
	
	
	@AfterEach
	void deleteDB() {
		taskRepository.deleteAll();
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
	
	
	
	
	/*@Test
	void getTaskByIdTest() {
		Task task = taskService.getTaskById(0L);
		assertNotNull(task);
		assertEquals(task.getId(),(Long)0L);
		assertEquals(task.getDescription(),DESC_1);
		
	}
	
	
	@Test
	void getAllTest() {
		//ArrayList<Task> tasks = taskService.getAll();
		assertEquals(tasks.size(),2);
		
		
		assertNotNull(tasks.get(0));
		assertEquals(tasks.get(0).getId(),(Long)0L);
		assertEquals(tasks.get(0).getDescription(),DESC_1);
		
		assertNotNull(tasks.get(1));
		assertEquals(tasks.get(1).getId(),(Long)1L);
		assertEquals(tasks.get(1).getDescription(),DESC_2);
		
	}
	
	@Test
	void createTaskTest() {
		Task task = new Task();
		task.setDescription("Task 3");

		Task task2 = taskService.createTask(task);
		assertNotNull(task2);
		assertEquals(task2.getId(),(Long)2L);
		assertEquals(task2.getDescription(),"Task 3");
	}
	
	
	@Test
	void updateNonExistingTaskTest() {
		assertEquals(2,2);
	}
	
	@Test
	void updateDescriptionTaskTest() {
		assertEquals(2,2);
	}
	
	@Test
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
