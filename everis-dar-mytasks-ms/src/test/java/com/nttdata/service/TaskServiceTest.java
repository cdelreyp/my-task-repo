package com.nttdata.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import com.nttdata.repository.entities.Task;
import com.nttdata.service.TaskService;
import com.nttdata.service.dto.TaskAddDTO;
import com.nttdata.service.dto.TaskDTO;
import com.nttdata.service.dto.TaskUpdateDTO;
import com.nttdata.utils.enums.enumStatus;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TaskServiceTest {
	
	private final static String DESC_TASK_1 = "TASK PRUEBA 1";
	private final static String DESC_TASK_4 = "TASK PRUEBA 4";
	private final static String UPDATED_USER_NAME = "NEW USER";
	
	
	@Autowired
	private TaskService taskService;
	
	
	//** Get all tasks   **//
	@Test
	@Order(1)
	void getAllTest() {
		ArrayList<TaskDTO> tasks = taskService.getAllTasks();
		assertEquals(tasks.size(),3);
	}
	
	//** Get all tasks by status   **//
	@Test
	@Order(2)
	void getAllByStatusTest() {
		ArrayList<TaskDTO> tasks = taskService.getAllByStatus(enumStatus.IN_PROGRESS);
		assertEquals(tasks.size(),2);
		for (TaskDTO task : tasks) {
			assertEquals(task.getStatus(),enumStatus.IN_PROGRESS);
		}
		
		tasks = taskService.getAllByStatus(enumStatus.PENDING);
		assertEquals(tasks.size(),1);
		for (TaskDTO task : tasks) {
			assertEquals(task.getStatus(),enumStatus.PENDING);
		}
	}
	
	//** Get task by id   **//
	@Test
	@Order(3)
	void getPresentTaskByIdTest() {
		Task task = taskService.getTaskById(1L);
		assertNotNull(task);
		assertEquals(task.getId(),(Long)1L);
		assertEquals(task.getDescription(),DESC_TASK_1);
	}
	
	@Test
	@Order(4)
	void getNonPresentTaskByIdTest() {
		Task task = taskService.getTaskById(-1L);
		assertNull(task);
	}
	
	//** add new task   **//
	@Test
	@Order(5)
	void addTaskTest() {
		TaskAddDTO task = new TaskAddDTO();
		task.setDescription(DESC_TASK_4);

		Task task2 = taskService.addTask(task);
		assertNotNull(task2);
		assertEquals(task2.getId(),(Long)4L);
		assertEquals(task2.getDescription(),DESC_TASK_4);
		assertEquals(task2.getStatus(),enumStatus.IN_PROGRESS);
	}
	
	
	//** updated task   **//
	
	@Test
	@Order(6)
	void updateNonExistingTaskTest() {
		assertNull(taskService.updateTask(null, -1L));
	}
	
	
	@Test
	@Order(7)
	void updateDescriptionNonDeletedTaskTest() {
		TaskUpdateDTO task = new TaskUpdateDTO();
		task.setDescription(DESC_TASK_1);
		Task taskUpdated = taskService.updateTask(task, 1L);
		assertEquals(DESC_TASK_1,taskUpdated.getDescription());
	}
	
	@Test
	@Order(8)
	void updateStatusNonDeletedTaskTest() {
		TaskUpdateDTO task = new TaskUpdateDTO();
		task.setStatus(enumStatus.COMPLETED);
		Task taskUpdated = taskService.updateTask(task, 1L);
		assertEquals(enumStatus.COMPLETED,taskUpdated.getStatus());
	}
	
	@Test
	@Order(9)
	void updateUserNonDeletedTaskTest() {
		TaskUpdateDTO task = new TaskUpdateDTO();
		task.setUserAsigned(UPDATED_USER_NAME);
		Task taskUpdated = taskService.updateTask(task, 1L);
		assertEquals(UPDATED_USER_NAME,taskUpdated.getUserAsigned());
	}
	
	
	//** delete  task   **//
	@Test
    @Order(10)
    void deleteTaskTest() {
        Task task = taskService.getTaskById(1L);
        assertNotNull(task);
        assertEquals(task.getStatus(),enumStatus.COMPLETED);
        
        taskService.deleteTask(1L);
        task = taskService.getTaskById(1L);
        assertNotNull(task);
        assertEquals(task.getStatus(),enumStatus.DELETED);
	}
	
	//** update task   **//
	
	@Test
	@Order(11)
	void updateStatusDeletedTaskTest() {
		TaskUpdateDTO task = new TaskUpdateDTO();
		task.setStatus(enumStatus.COMPLETED);
		Task taskUpdated = taskService.updateTask(task, 1L);
		assertNull(taskUpdated);
	}
	
}
