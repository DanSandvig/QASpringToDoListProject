package com.bae.qaspringtodolistproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.qaspringtodolistproject.domain.ToDoListEntry;
import com.bae.qaspringtodolistproject.repo.ToDoListEntryRepo;

@Service
public class ToDoListEntryService {
	
	private ToDoListEntryRepo toDoListEntryRepo;

	public ToDoListEntryService(ToDoListEntryRepo toDoListEntryRepo) {
		super();
		this.toDoListEntryRepo = toDoListEntryRepo;
	}
	
	//Methods
	
	//Create
	public ToDoListEntry create(ToDoListEntry toDoListEntry) {
		return toDoListEntryRepo.saveAndFlush(toDoListEntry);
	}
	
	//Read
	public List<ToDoListEntry> getAll() {
		return toDoListEntryRepo.findAll();
	}
	
	public ToDoListEntry getById(long id) {
		return toDoListEntryRepo.findById(id).get();
	}
	
	public ToDoListEntry getByTitle(String title) {
		return toDoListEntryRepo.findToDoListEntryByTitle(title);
	}
	
	//Update
	
	public ToDoListEntry update(long id, ToDoListEntry updatedEntry) {
		ToDoListEntry existingEntry = toDoListEntryRepo.findById(id).get();
		
		//Set new properties on existing item
		existingEntry.setTitle(updatedEntry.getTitle());
		existingEntry.setDescription(updatedEntry.getDescription());
		existingEntry.setPriority(updatedEntry.getPriority());
		existingEntry.setComplete(updatedEntry.isComplete());
		
		//Save the object back to the database and return to front end
		return toDoListEntryRepo.saveAndFlush(existingEntry);
		
	}
	
	//Delete
	
	public boolean delete(long id) {
		toDoListEntryRepo.findById(id).get();
		
		toDoListEntryRepo.deleteById(id);
		
		boolean success = !toDoListEntryRepo.existsById(id);
		return success;
	}
}
