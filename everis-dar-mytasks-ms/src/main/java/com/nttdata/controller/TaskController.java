package com.nttdata.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.Task;

@RestController
public class TaskController {
	
	
	
	
	@GetMapping("/tasks")
	public String getTasks(){
		
		//Task task = new Task();
		
		//task.setId(1);
		
		return "hola";
	}

}
