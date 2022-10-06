package com.nttdata.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.Task;
import com.nttdata.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	/**
     * GET OPERATION 
     * @return all Tasks (JSON Array), 200 OK
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<ArrayList<Task>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.get());
    }

    /**
     * POST OPERATION
     * @param task request body (Task to insert)
     * @return inserted Task (JSON), 201 CREATED or 409 CONFLICT
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Task> post(@RequestBody Task task){ 
        if(taskService.getByKey(task.getId()).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); 
        else {
            this.taskService.post(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        } 
    }

    /**
     * PUT OPERATION
     * @param task request body (Task to update)
     * @param id path variable (id of the task to update)
     * @return updated Task (JSON), 200 OK or 404 NOT FOUND
     */
    @PutMapping(value = "{id}", consumes = "application/json")
    public ResponseEntity<Task> put(@RequestBody Task task, @PathVariable("id") Long id){
        if(!taskService.getByKey(id).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        else {
            this.taskService.put(task, id);
            return ResponseEntity.status(HttpStatus.OK).body(task); 
        }
    }

    /**
     * DELETE OPERATION
     * @param id path variable (id of the task to delete)
     * @return empty response body, 204 NO CONTENT or 404 NOT FOUND
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        if(!taskService.getByKey(id).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else {
            this.taskService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    /**
     * GET BY KEY OPERATION
     * @param id path variable (id of the Task to retrieve)
     * @return Task that matchs the key (JSON), 200 OK or 404 NOT FOUND 
     */
    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Task> getByKey(@PathVariable("id") Long id){
        if(!taskService.getByKey(id).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);    
        else 
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getByKey(id).get());
    }

}
