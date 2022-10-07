package com.nttdata.controller;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.repository.entities.Task;
import com.nttdata.utils.enums.enumStatus;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
public class TaskControllerTests {

	private WebTestClient webTestClient;
	
	private final static String BASE_URL = "localhost:8080";
	private final static String ABSOLUTE_URI = BASE_URL + "/task";
	
	@BeforeEach
	public void init() {
		this.webTestClient = WebTestClient.bindToServer().baseUrl(BASE_URL).build();
	}
	
	@Test
	@Order(1)
	public void test01_post() {
		// Initialization: Task object to insert
		Task task = new Task(4L, "description", enumStatus.IN_PROGRESS, "creator");
		// POST - WebTestClient
		this
		.webTestClient
		.post()
		.uri(ABSOLUTE_URI)
		.bodyValue(task)
		.exchange()
		.expectStatus().isCreated()
		.expectBody(Task.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response must not be null");
			assertEquals(response.getStatus(), HttpStatus.CREATED, "HTTP status code must be 201 CREATED");
			// Testing response body
			assertNotNull(response.getResponseBody(), "Response body must not be null");
			assertTrue(response.getResponseBody() instanceof Task, "Response body must be a Task object");
			assertEquals(response.getResponseBody().getId(), 4L, "Task ids must be equals");
			assertEquals(response.getResponseBody().getDescription(), "description", "Task descriptions must be equals");
			assertEquals(response.getResponseBody().getStatus(), enumStatus.IN_PROGRESS, "Task status must be equals");	
		});
	}
	
	@Test
	@Order(2)
	public void test02_put() {
		// Initialization: Task object to update
		Task task = new Task(1L, "updated description", enumStatus.COMPLETED, "creator");
		// POST - WebTestClient
		this
		.webTestClient
		.put()
		.uri(ABSOLUTE_URI + "/1")
		.bodyValue(task)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Task.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response must not be null");
			assertEquals(response.getStatus(), HttpStatus.OK, "HTTP status code must be 200 OK");
			// Testing response body
			assertNotNull(response.getResponseBody(), "Response body must not be null");
			assertTrue(response.getResponseBody() instanceof Task, "Response body must be a Task object");
			assertEquals(response.getResponseBody().getId(), 1L, "Task ids must be equals");
			assertEquals(response.getResponseBody().getDescription(), "updated description", "Task descriptions must be equals");
			assertEquals(response.getResponseBody().getStatus(), enumStatus.COMPLETED, "Task status must be equals");	
		});
	}
	
	@Test
	@Order(3)
	public void test03_get() {
		this.webTestClient
		.get()
		.uri(ABSOLUTE_URI + "s")
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Task.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response must not be null");
			assertEquals(response.getStatus(), HttpStatus.OK, "HTTP status code must be 200 OK");
			// Testing response body
			Task responseBody = response.getResponseBody().get(0);
			assertNotNull(responseBody, "Response body must not be null");
			assertTrue(responseBody instanceof Task, "Response body must be a Task object");
			assertEquals(responseBody.getId(), 1L, "Task ids must be equals");
			assertEquals(responseBody.getDescription(), "updated description", "Task descriptions must be equals");
			assertEquals(responseBody.getStatus(), enumStatus.COMPLETED, "Task status must be equals");	
		});
	}
	
	@Test
	@Order(4)
	public void test04_getById() {
		// GET by id - WebTestClient
		this
		.webTestClient
		.get()
		.uri(ABSOLUTE_URI + "/1")
		.exchange()
		.expectStatus().isOk()
		.expectBody(Task.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response must not be null");
			assertEquals(response.getStatus(), HttpStatus.OK, "Status code must be 200 OK");
			// Testing response body
			Task responseBody = response.getResponseBody();
			assertNotNull(responseBody, "Response body must not be null");
			assertTrue(responseBody instanceof Task, "Response body must be a Task object");
			assertEquals(responseBody.getId(), 1L, "Task ids must be equals");
			assertEquals(responseBody.getDescription(), "updated description", "Task descriptions must be equals");
			assertEquals(responseBody.getStatus(), enumStatus.COMPLETED, "Task status must be equals");	
		});
	}
	
	@Test
	@Order(5)
	public void test05_getByStatus() {
		this.webTestClient
		.get()
		.uri(ABSOLUTE_URI + "sByStatus?status=COMPLETED")
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Task.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response must not be null");
			assertEquals(response.getStatus(), HttpStatus.OK, "HTTP status code must be 200 OK");
			// Testing response body
			Task responseBody = response.getResponseBody().get(0);
			assertNotNull(responseBody, "Response body must not be null");
			assertTrue(responseBody instanceof Task, "Response body must be a Task object");
			assertEquals(responseBody.getId(), 1L, "Task ids must be equals");
			assertEquals(responseBody.getDescription(), "updated description", "Task descriptions must be equals");
			assertEquals(responseBody.getStatus(), enumStatus.COMPLETED, "Task status must be equals");	
		});
	}
	
	@Test
	@Order(6)
	public void test06_delete() {
		// DELETE - WebTestClient
		this
		.webTestClient
		.delete()
		.uri(ABSOLUTE_URI + "/1")
		.exchange()
		.expectStatus().isNoContent()
		.expectBody(Void.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response entity must be not null");
			assertEquals(response.getStatus(), HttpStatus.NO_CONTENT, "HTTP status code must be 204 NO CONTENT");
			// Testing response body
			assertNull(response.getResponseBody(), "Response body must be null");	
		});
	}
	
}
