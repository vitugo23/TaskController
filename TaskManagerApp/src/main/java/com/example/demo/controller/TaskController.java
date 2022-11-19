package com.example.demo.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Task;
import com.example.demo.model.TaskRepository;
public class TaskController {


	@Autowired
	TaskRepository  tskrep;
	
	@GetMapping("/")
	public List<Task> getTask()
	{
		return tskrep.findAll();
	}
	@GetMapping("/tasks/assignedto")
	public ResponseEntity<List<Task>> getTasksByAssignedto (@RequestParam String assignedto){
		return new ResponseEntity<List<Task>> (tskrep.findAll(), HttpStatus.OK);
	}
	@GetMapping("/tasks/tasktitle")
	public ResponseEntity<List<Task>> getTasksByTasktitle (@RequestParam String tasktitle){
		return new ResponseEntity<List<Task>> (tskrep.findByTasktitle(tasktitle), HttpStatus.OK);
	}
	
	@PostMapping("/addTask")
	public Task addTask(@RequestBody Task tskobj)
	{
		Task t=tskrep.save(tskobj);
		return t;
	}

	@PutMapping("/updateTask")
	public Task updateTask(@RequestBody Task obj)
	{
		int taskid=obj.getTaskid();
		Optional<Task> updatetask=tskrep.findById(taskid);
		Task updatedTask=null;
		if(updatetask.isPresent())
		{
			updatedTask=updatetask.get();
			updatedTask.setTaskid(obj.getTaskid());
			updatedTask.setTasktitle(obj.getTasktitle());
			updatedTask.setDuration(obj.getDuration());
			updatedTask.setAssignedTo(obj.getAssignedTo());
			tskrep.save(updatedTask);
		}
		return updatedTask;
	}
	
	@DeleteMapping("/delete/{taskid}")
	public void  deleteTask(@PathVariable int taskid)
	{
	Optional<Task> deletetask=tskrep.findById(taskid);
	
		if(deletetask.isPresent())
		{ 
			deletetask=Optional.ofNullable(deletetask.get());
		tskrep.deleteById(taskid);
		}
	}
}
