package com.bae.qaspringtodolistproject.service;

import org.springframework.stereotype.Service;

import com.bae.qaspringtodolistproject.repo.ToDoListEntryRepo;

@Service
public class ToDoListEntryService {
	
	private ToDoListEntryRepo toDoListEntryRepo;

	public ToDoListEntryService(ToDoListEntryRepo toDoListEntryRepo) {
		super();
		this.toDoListEntryRepo = toDoListEntryRepo;
	}
	
	
}
