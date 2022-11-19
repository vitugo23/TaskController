package com.example.demo.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("/task/{taskid}")
	public Task getTsk(@PathVariable int taskid);
	{
		Optional<Task> tobj=tskrep.findById(taskid);
		Task retreiveobj=null;
		if(tobj.isPresent())
		{
			 retreiveobj=tobj.get();
		}
		return retreiveobj;	
	}
}

