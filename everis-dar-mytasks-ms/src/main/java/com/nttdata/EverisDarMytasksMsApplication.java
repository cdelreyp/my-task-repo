package com.nttdata;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.nttdata.model.Task;
import com.nttdata.repository.TaskRepository;

@SpringBootApplication
@EnableConfigurationProperties
public class EverisDarMytasksMsApplication {

	@Autowired
	private static TaskRepository taskRepository;
	
	public static void main(String[] args) {
	
		SpringApplication.run(EverisDarMytasksMsApplication.class, args);
		
		Task tarea1 = new Task();
		tarea1.setDescription("desc 1");
		tarea1.setEntry_date(new Timestamp(System.currentTimeMillis()));
		taskRepository.save(tarea1);
		
		Task tarea2 = new Task();
		tarea2.setDescription("desc 2");
		tarea2.setEntry_date(new Timestamp(System.currentTimeMillis()));
		taskRepository.save(tarea2);
		
	}
	


}
