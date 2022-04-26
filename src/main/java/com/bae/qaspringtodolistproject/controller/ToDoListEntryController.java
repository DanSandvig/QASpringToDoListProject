package com.bae.qaspringtodolistproject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.qaspringtodolistproject.service.ToDoListEntryService;

@CrossOrigin
@RestController
@RequestMapping("/todolist")
public class ToDoListEntryController {

	private ToDoListEntryService toDoListService;

	public ToDoListEntryController(ToDoListEntryService toDoListService) {
		super();
		this.toDoListService = toDoListService;
	}
	
}
